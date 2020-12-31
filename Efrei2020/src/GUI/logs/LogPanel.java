package GUI.logs;

import GUI.logs.Logs;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogPanel extends VBox  {

    VBox vBox;

    public LogPanel() throws FileNotFoundException {
        super();
        ScrollPane scrollPane = new ScrollPane();
        vBox = new VBox();
        FileInputStream inputstream = new FileInputStream("Efrei2020/src/Assets/log.png");
        AnchorPane anchorPane = new AnchorPane();
        Image logImage = new Image(inputstream);
        ImageView imageLog = new ImageView(logImage);
        scrollPane.setContent(vBox);
        scrollPane.setMinWidth(200);
        scrollPane.setMaxHeight(400);
        anchorPane.getChildren().add(scrollPane);
        anchorPane.getChildren().add(imageLog);
        AnchorPane.setTopAnchor(imageLog, 0.);
        AnchorPane.setTopAnchor(scrollPane, 100.);
        getChildren().add(anchorPane);
        setHeight(500);
        setWidth(400);
        getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
        vBox.getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
    }

    public void addPanel(Logs log){
        vBox.getChildren().add(0,((Node)log));
    }



}
