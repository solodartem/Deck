package sample.animation;

import javafx.scene.image.ImageView;

/**
 * Created by asolod on 16.04.17.
 */
public abstract class AbstractAnimationBuilder {

    protected double rearangeX(ImageView imageView) {
        return imageView.getParent().localToScene(imageView.getLayoutX(), imageView.getLayoutY()).getX() + imageView.getBoundsInParent().getWidth() / 2;
    }

    protected double rearangeY(ImageView imageView) {
        return imageView.getParent().localToScene(imageView.getLayoutX(), imageView.getLayoutY()).getY() + imageView.getBoundsInParent().getHeight() / 2;
    }
}
