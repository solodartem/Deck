package com.asolod.test.poker;

import com.asolod.test.poker.controller.MainStageController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("floorLayout.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Poker test");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add("file:resources/spreadsheet.css");


        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, window -> loader.<MainStageController>getController().cardAnimationPlay());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
