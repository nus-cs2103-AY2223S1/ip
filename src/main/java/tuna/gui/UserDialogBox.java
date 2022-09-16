package tuna.gui;

import javafx.scene.image.Image;

//@@author j-lum-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
//with minor modifications
/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class UserDialogBox extends DialogBox {

    private UserDialogBox(String text, Image img) {
        super(text, img, "/view/UserDialogBox.fxml");
    }

    public static UserDialogBox getUserDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
