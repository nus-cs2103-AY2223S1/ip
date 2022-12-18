package dukeprogram.userinterface;

import dukeprogram.facilities.User;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * DialogBubbleGroup is the collection of the shown dialog bubbles and the profile picture
 */
public class DialogBubbleGroup extends HBox {

    private VBox dialogBubbleContainer;

    /**
     * Creates a new DialogBubbleGroup for the user
     * @param spaceBetweenBubbles the space between the chat bubbles
     * @param picture the user's picture
     * @param parent the parent of this group
     */
    public DialogBubbleGroup(int spaceBetweenBubbles, Image picture, VBox parent) {
        dialogBubbleContainer = new VBox(spaceBetweenBubbles);

        ImageView displayPicture = new ImageView();

        setAlignment(Pos.BOTTOM_RIGHT);
        displayPicture.setImage(picture);
        getChildren().addAll(dialogBubbleContainer, displayPicture);

        new LayoutAnimator(true, true, 400)
                .observe(getChildren());

        new DropShadowCircleFrame(25, 25, 1, 1)
                .frame(displayPicture);

        parent.getChildren().add(this);
    }

    /**
     * Creates a new DialogBubbleGroup for Duke
     * @param spaceBetweenBubbles the space between the chat bubbles
     * @param parent the parent of this group
     */
    public DialogBubbleGroup(int spaceBetweenBubbles, VBox parent) {
        dialogBubbleContainer = new VBox(spaceBetweenBubbles);

        ImageView displayPicture = new ImageView();

        setAlignment(Pos.BOTTOM_LEFT);
        displayPicture.setImage(User.DUKE.getUserImage());
        getChildren().addAll(displayPicture, dialogBubbleContainer);

        new LayoutAnimator(true, true, 400)
                .observe(getChildren());

        new DropShadowCircleFrame(25, 25, 1, 1)
                .frame(displayPicture);

        parent.getChildren().add(this);
    }

    public VBox getDialogBubbleContainer() {
        return dialogBubbleContainer;
    }
}
