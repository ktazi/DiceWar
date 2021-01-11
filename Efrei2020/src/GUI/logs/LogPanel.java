package GUI.logs;

import GUI.logs.Logs;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogPanel extends VBox  {

    public Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    public double width =  screenBounds.getWidth();
    public double height =  screenBounds.getHeight();
    VBox vBox;

    public LogPanel() throws FileNotFoundException {
        super();
        ScrollPane scrollPane = new ScrollPane();
        vBox = new VBox();
        FileInputStream inputstream = new FileInputStream("Efrei2020/src/Assets/log.png");
        AnchorPane anchorPane = new AnchorPane();
        Image logImage = new Image(inputstream);
        ImageView imageLog = new ImageView(logImage);
        imageLog.setFitWidth((2*width)/12);
        imageLog.setFitHeight((height)/7);
        scrollPane.setContent(vBox);
        scrollPane.setMinWidth(width/6);
        scrollPane.setMaxHeight((4*height)/7);
        anchorPane.getChildren().add(scrollPane);
        anchorPane.getChildren().add(imageLog);
        AnchorPane.setTopAnchor(imageLog, 0.);
        AnchorPane.setTopAnchor(scrollPane, (1*height)/7);
        getChildren().add(anchorPane);
        setHeight((5*height)/7);
        setWidth(width/3);
        getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
        vBox.getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
    }

    public void addPanel(Logs log){
        vBox.getChildren().add(0,((Node)log));
    }



}
