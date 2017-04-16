package sample.animation;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private Pane moveLayer;

    private ImageView cardView;

    private void createControllers() {
        // init card back
        this.cardView = new ImageView(CARD_BACK_URL);
        this.cardView.setFitWidth(this.deck.getBoundsInParent().getWidth());
        this.cardView.setFitHeight(this.deck.getBoundsInParent().getHeight());
        this.moveLayer.getChildren().add(this.cardView);
        this.cardView.toFront();

    }

    public SingleCardMoveBuilder fromDeck(ImageView deck) {
        this.deck = deck;
        return this;
    }

    public SingleCardMoveBuilder toSlot(ImageView cardSlot) {
        this.cardSlot = cardSlot;
        return this;
    }

    public SingleCardMoveBuilder overPane(Pane moveLayer) {
        this.moveLayer = moveLayer;
        return this;
    }

    public Animation build() {
        createControllers();

        SequentialTransition moveRotateFlip = new SequentialTransition(this.cardView, moveRotate(), flipCardBack(), flipCardFace());
        moveRotateFlip.play();
        return moveRotateFlip;
    }

    private ParallelTransition moveRotate() {
        rotate();
        ParallelTransition moveRotate = new ParallelTransition(this.cardView, move());
        return moveRotate;
    }

    private Animation move() {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.setPath(new Line(rearangeX(this.deck), rearangeY(this.deck),
                rearangeX(this.cardSlot), rearangeY(this.cardSlot)));
        return pathTransition;
    }

    private void rotate() {
        Rotate rotationTransform = new Rotate(0,50,50);
        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(
                                        rotationTransform.angleProperty(),
                                        360
                                )
                        )
                );
        rotationAnimation.play();
        this.cardView.getTransforms().addAll(rotationTransform);
    }

    private Animation flipCardBack() {
        RotateTransition flipCardBack = new RotateTransition();
        flipCardBack.setNode(this.cardView);
        flipCardBack.setDuration(new Duration(DURATION / 4));
        flipCardBack.setAxis(Rotate.Y_AXIS);
        flipCardBack.setFromAngle(0.0);
        flipCardBack.setToAngle(90.0);
        flipCardBack.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SingleCardMoveBuilder.this.cardView.setImage(new Image(CARD_FACE_URL));
            }
        });
        return flipCardBack;
    }

    private Animation flipCardFace() {
        RotateTransition flipCardBack = new RotateTransition();
        flipCardBack.setNode(this.cardView);
        flipCardBack.setDuration(new Duration(DURATION / 4));
        flipCardBack.setAxis(Rotate.Y_AXIS);
        flipCardBack.setFromAngle(90);
        flipCardBack.setToAngle(0);
        flipCardBack.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SingleCardMoveBuilder.this.cardView.setImage(new Image(CARD_BACK_URL));
            }
        });
        return flipCardBack;
    }

}
