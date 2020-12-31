package GUI.BattleBar;

import GUI.Utils.Game;
import GUI.logs.BattlePanel;
import Gameplay.Dices;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
public class DuelPanel extends AnchorPane {

    ArrayList<ImageView> dices;
    SelectionTerritoryPanel selectionTerritoryPanel;
    ImageView spriteJ1;
    ImageView spriteJ2;
    Button battle;
    Button attackAgain;
    BattleBar parent;
    int scoreJ1;
    int scoreJ2;
    Text teamJ1;
    Text teamJ2;
    Text score1;
    Text score2;

    public DuelPanel(SelectionTerritoryPanel selectionTerritoryPanel, BattleBar battleBar) {
        super();
        scoreJ1 = 0;
        scoreJ2 = 0;
        parent = battleBar;
        this.selectionTerritoryPanel = selectionTerritoryPanel;
        Canvas canvas = new Canvas();
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.setHeight(200);
        canvas.setWidth(1200);
        canvas.getGraphicsContext2D().fillRect(0, 0, 1200, 200);
        ImageView battleImage = new ImageView(new Image("Assets/battle.png"));
        getChildren().add(canvas);
        getChildren().add(battleImage);
        AnchorPane.setLeftAnchor(battleImage, 450.);
        spriteJ1 = new ImageView(new Image("Assets/Blank.png"));
        spriteJ2 = new ImageView(new Image("Assets/Blank.png"));
        getChildren().add(spriteJ1);
        AnchorPane.setTopAnchor(spriteJ1, 20.);
        AnchorPane.setLeftAnchor(spriteJ1, 300.);
        getChildren().add(spriteJ2);
        AnchorPane.setTopAnchor(spriteJ2, 20.);
        AnchorPane.setLeftAnchor(spriteJ2, 775.);
        teamJ1 = new Text("-");
        teamJ1.setFill(Color.WHITE);
        teamJ2 = new Text("-");
        teamJ2.setFill(Color.WHITE);
        getChildren().add(teamJ1);
        AnchorPane.setTopAnchor(teamJ1, 150.);
        AnchorPane.setLeftAnchor(teamJ1, 350.);
        getChildren().add(teamJ2);
        AnchorPane.setTopAnchor(teamJ2, 150.);
        AnchorPane.setLeftAnchor(teamJ2, 820.);
        score1 = new Text("0");
        score1.setFill(Color.WHITE);
        score2 = new Text("0");
        score2.setFill(Color.WHITE);
        getChildren().add(score1);
        getChildren().add(score2);
        AnchorPane.setTopAnchor(score1, 90.);
        AnchorPane.setLeftAnchor(score1, 460.);
        AnchorPane.setTopAnchor(score2, 90.);
        AnchorPane.setLeftAnchor(score2, 700.);
        dices = new ArrayList<>();
        for (int h = 0; h < 2; h++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    dices.add(new ImageView(new Image("Assets/dice0.png")));
                    getChildren().add(dices.get((h * 8) + (i * 4) + j));
                    AnchorPane.setLeftAnchor(dices.get((h * 8) + (i * 4) + j), 100. + (40. * j) + (h * 850));
                    AnchorPane.setTopAnchor(dices.get((h * 8) + (i * 4) + j), 60. + (40. * i));
                }
            }
        }
        battle = new Button("FIGHT");
        battle.getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
        battle.setOnMouseClicked(event -> duel());
        getChildren().add(battle);
        AnchorPane.setTopAnchor(battle, 70.);
        AnchorPane.setLeftAnchor(battle, 550.);
        attackAgain = new Button("Resume Turn");
        attackAgain.setOnMouseClicked(event -> endDuel());
        attackAgain.getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
    }

    public void update(){
        teamJ1.setText(Game.colorToString(this.selectionTerritoryPanel.getTerritory1Selected().getPlayer().getColor()));
        teamJ1.setFill(Game.colorRgb(this.selectionTerritoryPanel.getTerritory1Selected().getPlayer().getColor()));
        teamJ2.setText(Game.colorToString(this.selectionTerritoryPanel.getTerritory2Selected().getPlayer().getColor()));
        teamJ2.setFill(Game.colorRgb(this.selectionTerritoryPanel.getTerritory2Selected().getPlayer().getColor()));
        score1.setText("0");
        score2.setText("0");
        spriteJ1.setImage(new Image("Assets/"+
                Game.forceToString(selectionTerritoryPanel.getTerritory1Selected().getForce()) +
                "_"+
                Game.colorToString(selectionTerritoryPanel.getTerritory1Selected().getPlayer().getColor()) + ".png"));
        spriteJ2.setImage(new Image("Assets/"+
                Game.forceToString(selectionTerritoryPanel.getTerritory2Selected().getForce()) +
                "_"+
                Game.colorToString(selectionTerritoryPanel.getTerritory2Selected().getPlayer().getColor()) + ".png"));
    }

    public void duel(){
        scoreJ1 = 0;
        scoreJ2 = 0;
        for (int h = 0; h<2;h++){
            for (int i = 0; i < 2; i++){
                for (int j = 0; j< 4; j++){
                    dices.get((h*8)+(i*4) + j).setImage(new Image("Assets/dice0.png"));
                }
            }
        }
        ArrayList<Integer> diceJ1 = Dices.throwDices(selectionTerritoryPanel.getTerritory1Selected().getForce());
        ArrayList<Integer> diceJ2 = Dices.throwDices(selectionTerritoryPanel.getTerritory2Selected().getForce());
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 8; j++){
                dices.get((i*8)+j).setImage(new Image("Assets/dice"+(i==0?diceJ1.get(j):diceJ2.get(j))+".png"));
                if (i==0)
                    scoreJ1 +=diceJ1.get(j);
                else
                    scoreJ2 += diceJ2.get(j);
            }
        }
        parent.logPanel.addPanel(new BattlePanel(
                selectionTerritoryPanel.getTerritory1Selected().getPlayer().getColor(),
                selectionTerritoryPanel.getTerritory2Selected().getPlayer().getColor(),
                scoreJ1,
                scoreJ2,
                scoreJ1>scoreJ2
        ));


        score1.setText(Integer.toString(scoreJ1));
        score2.setText(Integer.toString(scoreJ2));
        getChildren().remove(battle);
        getChildren().add(attackAgain);
        AnchorPane.setTopAnchor(attackAgain, 70.);
        AnchorPane.setLeftAnchor(attackAgain, 550.);
    }
    public void endDuel(){
        teamJ1.setText("-");
        teamJ1.setFill(Color.WHITE);
        teamJ2.setText("-");
        teamJ2.setFill(Color.WHITE);
        getChildren().remove(attackAgain);
        getChildren().add(battle);
        for (ImageView die : dices){
            die.setImage(new Image("Assets/dice0.png"));
        }
        AnchorPane.setTopAnchor(battle, 70.);
        AnchorPane.setLeftAnchor(battle, 550.);
        if (scoreJ1>scoreJ2){
            selectionTerritoryPanel.getTerritory1Selected().getPlayer().winningABattle(
                    selectionTerritoryPanel.getTerritory2Selected(),
                    selectionTerritoryPanel.getTerritory1Selected());
        }
        else{
            selectionTerritoryPanel.getTerritory1Selected().getPlayer().losingABattle(
                    selectionTerritoryPanel.getTerritory1Selected());
        }

        parent.switchMode();
    }

}
