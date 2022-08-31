package duke.ui;

import duke.Duke;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeParseException;

/**
 * Controller for MainWindow.
 *
 * @author Elgin
 */
public class MainWindow extends VBox {
    @FXML
    private TextField userInput;

    private Duke duke;

    /**
     * Setter for Duke.
     *
     * @param duke
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    private void submitHandler() {
        String input = userInput.getText();
        try {
            duke.handleUserInput(input);
        } catch (DukeException e) {
//            this.ui.showDukeError(e.getMessage());
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
//            this.ui.showNumberCastError();
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
//            this.ui.showInvalidDateError();
        }
    }

}
