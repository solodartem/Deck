package sample.animation;

import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by asolod on 16.04.17.
 */
public class FiveCardMoveAnimation {

    private SequentialTransition st = new SequentialTransition();

    public FiveCardMoveAnimation(ImageView deck, ImageView[] cardsSlots, Pane moveLayer) {

        for (ImageView cardSlot : cardsSlots) {
            st.getChildren().add(new SingleCardMoveBuilder().fromDeck(deck).toSlot(cardSlot).overPane(moveLayer).build());
        }
    }

    public void play() {
        this.st.play();
    }

}
