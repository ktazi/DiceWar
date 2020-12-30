package GUI.BattleBar;

import javafx.scene.layout.AnchorPane;

public class BattleBar extends AnchorPane {
    DuelPanel duelPanel;
    SelectionTerritoryPanel selectionTerritoryPanel;
    boolean battle;

    public BattleBar() {
        super();
        selectionTerritoryPanel = new SelectionTerritoryPanel(this);
        duelPanel = new DuelPanel(selectionTerritoryPanel, this);
        getChildren().add(selectionTerritoryPanel);
        battle = false;
    }

    public void switchMode(){
        getChildren().clear();
        getChildren().add(!battle?duelPanel:selectionTerritoryPanel);
        duelPanel.update();
        selectionTerritoryPanel.switchActive();
        battle = !battle;
    }

    public SelectionTerritoryPanel getSelectionTerritoryPanel(){
        return selectionTerritoryPanel;
    }

}
