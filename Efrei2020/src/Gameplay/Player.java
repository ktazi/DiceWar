package Gameplay;

import GUI.Game;

import java.util.ArrayList;

public class Player {
    private Game.COLOR color;
    ArrayList<Territory> territories;
    int idPlayer;
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
        System.out.println(force);

        while (!maxedForce() && force>0){
            int te;
            do{
                te = (int)(Math.random()*territories.size());
            }while(territories.get(te).getForce()==8);
            territories.get(te).setForce(territories.get(te).getForce()+1);
            force--;
            System.out.println(force);
        }
    }
}
