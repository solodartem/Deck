package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.animation.MoveCardAnimation;

public class Main extends Application {

    @FXML
    private ImageView deck;

    @FXML
    private  ImageView pokerTable;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Deck test");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add("file:resources/spreadsheet.css");


        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, new  EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent window)
            {
                new MoveCardAnimation((Pane) root.lookup("#floor")).play();
            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
