package sample.animation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Created by asolod on 16.04.17.
 */
public class NewAttempt {

    public static final String CARD_FACE_URL = "file:resources/../resources/img/2_of_spades.png";
    public static final String CARD_BACK_URL = "file:resources/../resources/img/back_side.png";

    final Duration SEC_2 = Duration.millis(2000);

    private final BorderPane field;
    private final ImageView deck;
    private ImageView cardView;

    public NewAttempt(BorderPane field, String cardFaceURL) {
        this.field = field;
        this.deck = (ImageView) field.lookup("#deck");

        initCardBack();
    }

    private void initCardBack() {
        // init card back
        this.cardView = new ImageView(CARD_BACK_URL);
        this.cardView.setFitWidth(this.deck.getBoundsInParent().getWidth());
        this.cardView.setFitHeight(this.deck.getBoundsInParent().getHeight());
        this.field.getChildren().add(this.cardView);

    }

    public void play() {

        final Rotate rotationTransform = new Rotate(0, 50, 50);
        this.cardView.getTransforms().add(rotationTransform);

        // rotate a square using timeline attached to the rotation transform's angle property.
        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
                .addAll(
                        new KeyFrame(
                                Duration.seconds(3),
                                 new KeyValue(
                                        rotationTransform.angleProperty(),
                                        360
                                ),

                                new KeyValue(this.cardView.xProperty(), 200),
                                new KeyValue(this.cardView.yProperty(), 200)
                        ),

                        new KeyFrame(
                                Duration.seconds(3),
                                /*new KeyValue(
                                        rotationTransform.axisProperty(),
                                        Rotate.Y_AXIS
                                ),*/
                                new KeyValue(
                                        rotationTransform.angleProperty(),
                                        -360
                                )
                        )
                );
        rotationAnimation.setCycleCount(Animation.INDEFINITE);
        rotationAnimation.play();
    }
}
