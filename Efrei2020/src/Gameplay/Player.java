package Gameplay;

import GUI.Utils.Game;
import Serialization.PlayerClone;

import java.io.Serializable;
import java.util.ArrayList;

public class Player {

    private final Game.COLOR color;
    ArrayList<Territory> territories;
    final int idPlayer;
    boolean eliminated;

    public Player(Game.COLOR color, ArrayList<Territory> territories, int idPlayer){
        this.color = color;
        this.territories = territories;
        eliminated = false;
        this.idPlayer = idPlayer;

    }
    public static ArrayList<Player> createPlayers(int nb, ArrayList<Territory> territories){
        ArrayList<Player> players = new ArrayList<>();
        int nbOfTerritories = territories.size()/nb;
        for (int i = 0; i < nb; i++){
            players.add(new Player(Game.COLOR.getColor(i), new ArrayList<>(), i));
            for (int j = 0; j < nbOfTerritories; j++){
                Territory territory;
                do {
                   territory = territories.get((int)(Math.random() * territories.size()));
                }while (territory.getPlayer() != null);
                players.get(i).getTerritories().add(territory);
                territory.setPlayer(players.get(i));
            }
            players.get(i).addForce(nbOfTerritories*2);
        }
        return players;
    }
    public ArrayList<Territory> getTerritories(){
        return this.territories;
    }
    public boolean isEliminated() {
        return eliminated;
    }
    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }
    public Game.COLOR getColor(){
        return color;
    }
    public boolean maxedForce(){
        for (Territory territory : territories){
            if (territory.getForce() != 8)
                return false;
        }
        return true;
    }
    public void addForce(int force){
        while (!maxedForce() && force>0){
            int te;
            do{
                te = (int)(Math.random()*territories.size());
            }while(territories.get(te).getForce()==8);
            territories.get(te).setForce(territories.get(te).getForce()+1);
            force--;
        }
    }
    public int getIdPlayer() {
        return idPlayer;
    }


    public void losingABattle(Territory territory){
        territory.setForce(1);
    }
    public void winningABattle(Territory newTerritory, Territory previousTerritory){
        newTerritory.getPlayer().getTerritories().remove(newTerritory);
        newTerritory.setPlayer(this);
        newTerritory.setForce(previousTerritory.getForce()-1);
        territories.add(newTerritory);
        previousTerritory.setForce(1);
    }

    public PlayerClone prepareSerialization(){
        return new PlayerClone(color, territories, idPlayer);
    }

    public int nbTerritoireContiguMax(){
        if (territories.size()<=0)
            return 0;
        ArrayList<ArrayList<Territory>> contigus = new ArrayList<>();
        contigus.add(new ArrayList<>());
        for (Territory territory : territories){
            boolean added = false;
            for (ArrayList<Territory> contigu : contigus){
                for(int i = 0; i < contigu.size() && !added; i++){
                    added = territory.areAdj(contigu.get(i));
                    if (added)
                        contigu.add(territory);
                }
            }
            if(!added){
                contigus.add(new ArrayList<>());
                contigus.get(contigus.size()-1).add(territory);
            }
        }
        int max = 0;
        for (ArrayList<Territory> contigu : contigus)
            max = Math.max(max, contigu.size());
        return max;
    }
}
