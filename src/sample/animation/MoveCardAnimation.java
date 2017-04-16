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
public class MoveCardAnimation {

    public static final String CARD_FACE_URL = "file:resources/../resources/img/2_of_spades.png";
    public static final String CARD_BACK_URL = "file:resources/../resources/img/back_side.png";

    public static final float DURATION = 2000;

    private final Pane moveLayer;
    private final ImageView deck;
    private final ImageView cardSlot1;

    private ImageView cardView;

    public MoveCardAnimation(Pane field) {
        this.deck = (ImageView) field.lookup("#deck");
        this.moveLayer = (Pane) field.lookup("#moveLayer");
        this.cardSlot1 = (ImageView) field.lookup("#cardSlot1");
        initCardBack();
        play();
    }

    private void initCardBack() {
        // init card back
        this.cardView = new ImageView(CARD_BACK_URL);
        this.cardView.setFitWidth(this.deck.getBoundsInParent().getWidth());
        this.cardView.setFitHeight(this.deck.getBoundsInParent().getHeight());
        this.moveLayer.getChildren().add(this.cardView);
        this.cardView.toFront();
    }


    public void play() {
        SequentialTransition moveRotateFlip = new SequentialTransition(this.cardView, moveRotate(), flipCardBack(), flipCardFace());
        moveRotateFlip.setCycleCount(10);
        moveRotateFlip.play();
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
                MoveCardAnimation.this.cardView.setImage(new Image(CARD_FACE_URL));
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
                MoveCardAnimation.this.cardView.setImage(new Image(CARD_BACK_URL));
            }
        });
        return flipCardBack;
    }

    private Animation moveRotate() {
        ParallelTransition moveRotate = new ParallelTransition(this.cardView, move());
        //rotate();
        return moveRotate;
    }

    private Animation move() {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.setPath(new Line(locateFromX(this.deck), locateFromY(this.deck),
                locateFromX(this.cardSlot1), locateFromY(this.cardSlot1)));
        return pathTransition;
    }

    private double locateFromX(ImageView imageView) {
        return imageView.getParent().localToScene(imageView.getLayoutX(), imageView.getLayoutY()).getX() + imageView.getBoundsInParent().getWidth() / 2;
    }

    private double locateFromY(ImageView imageView) {
        return imageView.getParent().localToScene(imageView.getLayoutX(), imageView.getLayoutY()).getY() + imageView.getBoundsInParent().getHeight() / 2;
    }

}
