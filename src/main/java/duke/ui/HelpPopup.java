package duke.ui;

import duke.command.HelpCommand;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the help window's popup.
 *
 * @author Kartikeya
 */
public class HelpPopup {
    private static final Image dukeImage = new Image("/images/DaDuke.png");

    /**
     * Displays help popup.
     */
    public static void display() {
        Stage popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Artemis - Help");
        popupWindow.getIcons().add(new Image("/images/DaDuke.png"));
        DialogBox dialog1 = DialogBox.getDukeDialog(HelpCommand.getHelpList(), dukeImage);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(dialog1);
        layout.setAlignment(Pos.CENTER);
        ScrollPane sp = new ScrollPane(); // Scrolling provided in case of overflow
        sp.setContent(layout);
        Scene scene1 = new Scene(sp, 300, 250);
        scene1.setFill(Color.LIGHTBLUE);
        popupWindow.setScene(scene1);
        popupWindow.setWidth(412); // Ideal width to prevent need for scrolling
        popupWindow.setHeight(637); // Ideal height to prevent need for scrolling
        popupWindow.show();
    }
}
