package GUI.BattleBar;

import GUI.Utils.Game;
import GUI.logs.BattlePanel;
import Gameplay.Dices;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.util.ArrayList;
public class DuelPanel extends AnchorPane {

    public Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    public double width =  screenBounds.getWidth();
    public double height =  screenBounds.getHeight();

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
        canvas.setHeight((2*height)/7);
        canvas.setWidth(width);
        canvas.getGraphicsContext2D().fillRect(0, 0, width, (2*height)/7);
        ImageView battleImage = new ImageView(new Image("Assets/battle.png"));
        battleImage.setFitWidth(width/4);
        battleImage.setFitHeight((0.5*height)/7);
        getChildren().add(canvas);
        getChildren().add(battleImage);
        AnchorPane.setLeftAnchor(battleImage, (4.5*width)/12);
        spriteJ1 = new ImageView(new Image("Assets/Blank.png"));
        spriteJ2 = new ImageView(new Image("Assets/Blank.png"));
        getChildren().add(spriteJ1);
        AnchorPane.setTopAnchor(spriteJ1, (0.2*height)/7);
        AnchorPane.setLeftAnchor(spriteJ1, (width)/4);
        getChildren().add(spriteJ2);
        AnchorPane.setTopAnchor(spriteJ2, (0.2*height)/7);
        AnchorPane.setLeftAnchor(spriteJ2, (7.75*width)/12);
        teamJ1 = new Text("-");
        teamJ1.setFill(Color.WHITE);
        teamJ2 = new Text("-");
        teamJ2.setFill(Color.WHITE);
        getChildren().add(teamJ1);
        AnchorPane.setTopAnchor(teamJ1, (1.5*height)/7);
        AnchorPane.setLeftAnchor(teamJ1, (3.5*width)/12);
        getChildren().add(teamJ2);
        AnchorPane.setTopAnchor(teamJ2, (1.5*height)/7);
        AnchorPane.setLeftAnchor(teamJ2, (8.2*width)/12);
        score1 = new Text("0");
        score1.setFill(Color.WHITE);
        score2 = new Text("0");
        score2.setFill(Color.WHITE);
        getChildren().add(score1);
        getChildren().add(score2);
        AnchorPane.setTopAnchor(score1, (0.9*height)/7);
        AnchorPane.setLeftAnchor(score1, (4.6*width)/12);
        AnchorPane.setTopAnchor(score2, (0.9*height)/7);
        AnchorPane.setLeftAnchor(score2, (7*width)/12);
        dices = new ArrayList<>();
        for (int h = 0; h < 2; h++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    dices.add(new ImageView(new Image("Assets/dice0.png")));
                    getChildren().add(dices.get((h * 8) + (i * 4) + j));
                    AnchorPane.setLeftAnchor(dices.get((h * 8) + (i * 4) + j), ((1*width)/12) + (((0.4*width)/12) * j) + (h * ((8.5*width)/12)));
                    AnchorPane.setTopAnchor(dices.get((h * 8) + (i * 4) + j), ((0.6*height)/7) + (((0.4*height)/7) * i));
                }
            }
        }
        battle = new Button("FIGHT");
        battle.getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
        battle.setOnMouseClicked(event -> duel());
        getChildren().add(battle);
        AnchorPane.setTopAnchor(battle, (0.7*height)/7);
        AnchorPane.setLeftAnchor(battle, (5.5*width)/12);
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
        AnchorPane.setTopAnchor(attackAgain, (0.7*height)/7);
        AnchorPane.setLeftAnchor(attackAgain, (5.5*width)/12);
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
        AnchorPane.setTopAnchor(battle, (0.7*height)/7);
        AnchorPane.setLeftAnchor(battle, (5.5*width)/12);
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
