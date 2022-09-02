package duke.gui;

import javafx.scene.image.Image;

/**
 * A controller for a User Dialog Box using FXML.
 */
public class UserDialogBox extends DialogBox {

    /**
     * Constructor for User Dialog Box using name, dialog, and image.
     *
     * @param nameText Text to put in the name label.
     * @param dialogText Text to put in the dialog text box.
     * @param image Profile picture for User.
     */
    private UserDialogBox(String nameText, String dialogText, Image image) {
        initialize("/view/UserDialogBox.fxml");
        this.name.setText(nameText);
        this.dialog.setText(dialogText);
        this.displayImage.setImage(image);
    }

    public static UserDialogBox of(String text, Image img) {
        return new UserDialogBox("You", text, img);
    }

}
