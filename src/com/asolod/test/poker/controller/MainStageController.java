package com.asolod.test.poker.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.asolod.test.poker.animation.FiveCardMoveAnimation;

import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements Initializable {

    @FXML
    private ImageView cardSlot1;

    @FXML
    private ImageView cardSlot2;

    @FXML
    private ImageView cardSlot3;

    @FXML
    private ImageView cardSlot4;

    @FXML
    private ImageView cardSlot5;

    @FXML
    private Pane floor;

    @FXML
    private ImageView deck;

    private ImageView[] cardSlots;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cardSlots = new ImageView[]{cardSlot1, cardSlot2, cardSlot3, cardSlot4, cardSlot5};
    }

    public void cardAnimationPlay() {
        new FiveCardMoveAnimation(this.deck, this.cardSlots, this.floor).play();
    }
}
