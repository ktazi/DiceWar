package GUI.BattleBar;

import GUI.logs.LogPanel;
import javafx.scene.layout.AnchorPane;

public class BattleBar extends AnchorPane  {
    DuelPanel duelPanel;
    SelectionTerritoryPanel selectionTerritoryPanel;
    boolean battle;
    LogPanel logPanel;

    public BattleBar(LogPanel logPanel) {
        super();
        this.logPanel = logPanel;
        selectionTerritoryPanel = new SelectionTerritoryPanel(this);
        duelPanel = new DuelPanel(selectionTerritoryPanel, this);
        getChildren().add(selectionTerritoryPanel);
        battle = false;
    }

    public LogPanel getLogPanel() {
        return logPanel;
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
