package com.asolod.test.poker.animation;

import com.asolod.test.poker.model.DeckModel;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by asolod on 16.04.17.
 */
public class FiveCardMoveAnimation {

    private SequentialTransition st = new SequentialTransition();
    private ImageView node;
    private DeckModel deckModel = new DeckModel();

    public FiveCardMoveAnimation(ImageView deck, ImageView[] cardsSlots, final Pane moveLayer) {

        iniNode(deck, moveLayer);
        for (ImageView cardSlot : cardsSlots) {
            Animation animationBuilder = new SingleCardMoveBuilder()
                    .fromDeck(deck)
                    .toSlot(cardSlot)
                    .usingCard(this.deckModel.popRandomCard())
                    .withNode(this.node)
                    .build();
            st.getChildren().add(animationBuilder);
        }
        st.setOnFinished(event -> moveLayer.getChildren().remove(node));
    }

    public void play() {
        this.st.play();
    }

    private void iniNode(ImageView deck, Pane moveLayer) {
        // init card back
        this.node = new ImageView(FiveCardsAnimationConfig.CARD_BACK_URL);
        this.node.setFitWidth(deck.getBoundsInParent().getWidth());
        this.node.setFitHeight(deck.getBoundsInParent().getHeight());
        moveLayer.getChildren().add(this.node);
        this.node.toFront();

    }

}
