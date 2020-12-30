package GUI.BattleBar;

import GUI.logs.BattlePanel;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BattleBar extends AnchorPane {
    DuelPanel duelPanel;
    SelectionTerritoryPanel selectionTerritoryPanel;
    boolean battle;

    public BattleBar() {
        super();
        selectionTerritoryPanel = new SelectionTerritoryPanel(this);
        duelPanel = new DuelPanel();
        getChildren().add(selectionTerritoryPanel);
        battle = false;
    }

    public void switchMode(){
        getChildren().clear();
        getChildren().add(!battle?duelPanel:selectionTerritoryPanel);
        battle = !battle;
    }

    public SelectionTerritoryPanel getSelectionTerritoryPanel(){
        return selectionTerritoryPanel;
    }

}
