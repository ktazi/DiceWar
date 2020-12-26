package GUI;

import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogPanel extends VBox {

    public LogPanel() throws FileNotFoundException {
        super();
        ScrollPane scrollPane = new ScrollPane();
        VBox root2 = new VBox();
        FileInputStream inputstream = new FileInputStream("Efrei2020/src/Assets/log.png");
        AnchorPane anchorPane = new AnchorPane();
        Image logImage = new Image(inputstream);
        ImageView imageLog = new ImageView(logImage);
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        root2.getChildren().add(new Text("test"));
        scrollPane.setContent(root2);
        scrollPane.setMinWidth(200);
        scrollPane.setMaxHeight(400);
        anchorPane.getChildren().add(scrollPane);
        anchorPane.getChildren().add(imageLog);
        AnchorPane.setTopAnchor(imageLog, 0.);
        AnchorPane.setTopAnchor(scrollPane, 100.);
        getChildren().add(anchorPane);
        setHeight(500);
        setWidth(400);
        getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());
        root2.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());
    }


}
