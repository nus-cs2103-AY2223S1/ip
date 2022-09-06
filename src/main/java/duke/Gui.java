package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Gui extends Ui {
    private VBox dialogContainer;
    private Image dukeImage;

    public Gui(VBox dialogContainer, Image dukeImage) {
        this.dialogContainer = dialogContainer;
        this.dukeImage = dukeImage;
    }

    public void printDivider() {
        return;
    }

    public void printWithDivider(String s) {
        this.println(s);
    }

    public void println(String s) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(s, dukeImage));
    }

    public void showWelcome() {
        this.println(super.welcomeMessage);
    }

    public void showLoadingError() {
        this.println(super.loadingErrorMessage);
    };
}
