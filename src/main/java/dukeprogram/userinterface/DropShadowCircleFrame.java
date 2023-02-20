package dukeprogram.userinterface;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * DropShadowCircle is a circle with the drop shadow that can frame any imageview within it.
 * Essentially it gives an ImageView circular corners.
 */
public class DropShadowCircleFrame extends Circle {

    private final double posX;
    private final double posY;
    private final Circle circularFrame;

    /**
     * Creates a DropShadowCircleFrame
     * @param posX the center X position where the circle will be
     * @param posY the center Y position where the circle will be
     * @param shadowOffsetX the shadow offset in the X position
     * @param shadowOffsetY the shadow offset in the Y position
     */
    public DropShadowCircleFrame(double posX, double posY,
                                 double shadowOffsetX, double shadowOffsetY) {
        this.posX = posX;
        this.posY = posY;

        circularFrame = new Circle(posX, posY, Math.min(posX, posY) - 5);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(shadowOffsetX);
        dropShadow.setOffsetY(shadowOffsetY);

        circularFrame.setEffect(dropShadow);
    }

    /**
     * Frames the ImageView within this DropShadowCircle
     * @param image the ImageView object to frame
     */
    public void frame(ImageView image) {
        image.setFitWidth(posX * 2);
        image.setFitHeight(posY * 2);
        image.setPickOnBounds(true);
        image.setPreserveRatio(true);

        image.setClip(circularFrame);
    }
}
