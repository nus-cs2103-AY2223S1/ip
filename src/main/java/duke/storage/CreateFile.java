package duke.storage;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;

import duke.Main;
import duke.exception.DukeException;
import duke.ui.MainWindow;

/**
 * Creates a txt file that should eventually be used for
 * the writing of a `TaskList` - in String format - to itself.
 * Note that this class is package-private.
 *
 * @author Kang Wei
 */
class CreateFile {

    /**
     * Creates a file in the specified location.
     *
     * @param filePath The location that the file should be created in.
     * @throws DukeException Throws a DukeException if an IOException is caught.
     */
    public static void makeFile(String filePath) throws DukeException {
        try {
            File file = new File(filePath);
        } catch (Exception e) {
            throw new DukeException("Honey! There was a problem with creating or obtaining "
                    + "your list of tasks at " + filePath + " ! :(");
        }
    }
}
