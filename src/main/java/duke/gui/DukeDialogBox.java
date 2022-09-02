package duke.gui;

import javafx.scene.image.Image;

/**
 * A controller for a Duke Dialog Box using FXML.
 */
public class DukeDialogBox extends DialogBox {

    /**
     * Constructor for Duke Dialog Box using name, dialog, and image.
     *
     * @param nameText Text to put in the name label.
     * @param dialogText Text to put in the dialog text box.
     * @param image Profile picture for Duke.
     */
    private DukeDialogBox(String nameText, String dialogText, Image image) {
        initialize("/view/DukeDialogBox.fxml");
        this.name.setText(nameText);
        this.dialog.setText(dialogText);
        this.displayImage.setImage(image);
    }

    public static DukeDialogBox of(String text, Image img) {
        return new DukeDialogBox("Duke", text, img);
    }

}
