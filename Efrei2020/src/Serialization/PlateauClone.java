package Serialization;

import GUI.BattleBar.BattleBar;
import GUI.BattleBar.SelectionTerritoryPanel;
import Gameplay.PlateauJeu;
import Gameplay.Player;
import Gameplay.Territory;
import Geometry.HexagonCase;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;

public class PlateauClone implements Serializable {
    private ArrayList<ArrayList<HexagonCaseClones>> tiles;
    private ArrayList<TerritoryClone> territories;
    private ArrayList<PlayerClone> players;
    private PlayerClone currentPlayer;

    public PlateauClone(ArrayList<ArrayList<HexagonCase>> til, ArrayList<Territory> territories,
                        ArrayList<Player> players, Player currentPlayer){
        this.tiles = new ArrayList<>();
        int i = 0;
        for (ArrayList<HexagonCase> line : til){
            this.tiles.add(new ArrayList<>());
            for (HexagonCase cas : line){
                this.tiles.get(i).add(cas.prepareSerialisation());
            }
            i++;
        }
        this.territories = new ArrayList<>();
        for (Territory t : territories){
            this.territories.add(t.prepareSerialization());
        }
        this.players = new ArrayList<>();
        for (Player player : players){
            this.players.add(player.prepareSerialization());
        }
        this.currentPlayer = currentPlayer.prepareSerialization();
    }

    public void serializeSelf() {
        ObjectOutputStream outputStream = null;
        try {
            File file = new File("./carte.sav");
            if (file.exists()){
                file.delete();
                file.createNewFile();
            }
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(this) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlateauJeu toPlateauJeu(SelectionTerritoryPanel territoryInfo, AnchorPane spritePane, BattleBar battleBar)
    {
        ArrayList<ArrayList<HexagonCase>> til = new ArrayList<>();
        int i = 0;
        for (ArrayList<HexagonCaseClones> line : this.tiles){
           til.add(new ArrayList<>());
            for (HexagonCaseClones cas : line){
                til.get(i).add(cas.convert());
            }
            i++;
        }
        ArrayList<Player> play = new ArrayList<>();
        for (PlayerClone player : players){
            play.add(player.convert(territoryInfo, spritePane));
        }
        ArrayList<Territory> territory = new ArrayList<>();
        i = 0;
        for (TerritoryClone t : this.territories){
            territory.add(t.convert(territoryInfo, spritePane));
            for (Player player : play) {
                if (player.getIdPlayer() == territory.get(i).getTempidplayer()){
                    territory.get(i).setPlayer(player);
                    player.getTerritories().add(territory.get(i));
                }
            }
            i++;
        }
        int currentPlayId = currentPlayer.convert(territoryInfo, spritePane).getIdPlayer();
        Player currentPlay = null;
        for (int j = 0; j < play.size(); j++){
            if (currentPlayId == play.get(j).getIdPlayer())
                currentPlay = play.get(j);
        }
        if (currentPlay == null)
            currentPlay = play.get(0);
        return new PlateauJeu(til, territory, play, currentPlay, battleBar);
    }


    public static PlateauJeu deserializeSelf(SelectionTerritoryPanel territoryInfo, AnchorPane spritePane, BattleBar battleBar){
        File fichier =  new File("./carte.sav") ;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fichier));
            PlateauClone plateauClone = (PlateauClone)objectInputStream.readObject();
            return plateauClone.toPlateauJeu(territoryInfo, spritePane, battleBar);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
