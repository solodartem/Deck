package sample.animation;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Created by asolod on 16.04.17.
 */
public class SingleCardMoveBuilder extends AbstractAnimationBuilder {

    public static final String CARD_FACE_URL = "file:resources/../resources/img/2_of_spades.png";
    public static final String CARD_BACK_URL = "file:resources/../resources/img/back_side.png";

    public static final float DURATION = 2000;

    private ImageView deck;
    private ImageView cardSlot;

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
        flipCardBack.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SingleCardMoveBuilder.this.node.setImage(new Image(CARD_FACE_URL));
            }
        });
        return flipCardBack;
    }

    private Animation flipCardFace() {
        RotateTransition flipCardBack = new RotateTransition();
        flipCardBack.setNode(this.node);
        flipCardBack.setDuration(new Duration(DURATION / 4));
        flipCardBack.setAxis(Rotate.Y_AXIS);
        flipCardBack.setFromAngle(90);
        flipCardBack.setToAngle(0);
        flipCardBack.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SingleCardMoveBuilder.this.cardSlot.setImage(new Image(CARD_FACE_URL));
                SingleCardMoveBuilder.this.node.setImage(new Image(CARD_BACK_URL));
            }
        });
        return flipCardBack;
    }

}
