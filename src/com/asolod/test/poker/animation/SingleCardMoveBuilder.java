package com.asolod.test.poker.animation;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import static com.asolod.test.poker.animation.FiveCardsAnimationConfig.CARD_BACK_URL;
import static com.asolod.test.poker.animation.FiveCardsAnimationConfig.CARD_FACE_URL_TEMPLATE;
import static com.asolod.test.poker.animation.FiveCardsAnimationConfig.DURATION;

/**
 * Created by asolod on 16.04.17.
 */
public class SingleCardMoveBuilder extends AbstractAnimationBuilder {

    private ImageView deck;
    private ImageView cardSlot;
    private String card;

    private ImageView node;

    public SingleCardMoveBuilder fromDeck(ImageView deck) {
        this.deck = deck;
        return this;
    }

    public SingleCardMoveBuilder toSlot(ImageView cardSlot) {
        this.cardSlot = cardSlot;
        return this;
    }

    public SingleCardMoveBuilder withNode(ImageView node) {
        this.node = node;
        return this;
    }

    public SingleCardMoveBuilder usingCard(String card){
        this.card = card;
        return this;
    }

    public Animation build() {
        SequentialTransition moveRotateFlip = new SequentialTransition(this.node, moveRotate(), flipCardBack(), flipCardFace());
        return moveRotateFlip;
    }

    private ParallelTransition moveRotate() {
        ParallelTransition moveRotate = new ParallelTransition(this.node, move(), rotate());
        return moveRotate;
    }

    private Animation move() {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(new Duration(DURATION/2));
        pathTransition.setPath(new Line(rearrangeX(this.deck), rearrangeY(this.deck),
                rearrangeX(this.cardSlot), rearrangeY(this.cardSlot)));
        return pathTransition;
    }

    private Timeline rotate() {
        Rotate rotationTransform = new Rotate(0, 50, 50);
        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
                .add(
                        new KeyFrame(
                                new Duration(DURATION/2),
                                new KeyValue(
                                        rotationTransform.angleProperty(),
                                        360
                                )
                        )
                );
        this.node.getTransforms().addAll(rotationTransform);
        return rotationAnimation;
    }

    private Animation flipCardBack() {
        RotateTransition flipCardBack = new RotateTransition();
        flipCardBack.setNode(this.node);
        flipCardBack.setDuration(new Duration(DURATION / 4));
        flipCardBack.setAxis(Rotate.Y_AXIS);
        flipCardBack.setFromAngle(0.0);
        flipCardBack.setToAngle(90.0);
        flipCardBack.setOnFinished(event -> SingleCardMoveBuilder.this.node.setImage(loadCardFaceImage()));
        return flipCardBack;
    }

    private Animation flipCardFace() {
        RotateTransition flipCardBack = new RotateTransition();
        flipCardBack.setNode(this.node);
        flipCardBack.setDuration(new Duration(DURATION / 4));
        flipCardBack.setAxis(Rotate.Y_AXIS);
        flipCardBack.setFromAngle(90);
        flipCardBack.setToAngle(0);
        flipCardBack.setOnFinished(event -> {
            SingleCardMoveBuilder.this.cardSlot.setImage(loadCardFaceImage());
            SingleCardMoveBuilder.this.node.setImage(new Image(CARD_BACK_URL));
        });
        return flipCardBack;
    }

    private Image loadCardFaceImage() {
        return new Image(String.format(CARD_FACE_URL_TEMPLATE, this.card));
    }
}
