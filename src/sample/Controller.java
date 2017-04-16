package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.animation.FiveCardMoveAnimation;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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

    @FXML
    protected Pane moveLayer;

    private ImageView[] cardSlots;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cardSlots = new ImageView[]{cardSlot1, cardSlot2, cardSlot3, cardSlot4, cardSlot5};
    }

    public void playCardAnimation() {
        new FiveCardMoveAnimation(this.deck, this.cardSlots, this.floor).play();
    }
}
