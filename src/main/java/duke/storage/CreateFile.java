package duke.storage;

import java.io.File;
import java.io.IOException;

import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Creates a txt file that should eventually be used for
 * the writing of a <code>Task[]</code> - in String format - to itself.
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
      if (file.createNewFile()) {
        Ui.print("Mumbot: A .txt file has been created in " +
            filePath + " , which will contain your list of tasks once this session " +
            "terminates <3");
      } else {
        Ui.print("Mumbot: A .txt file already existing in " +
            filePath + " will be used for loading your previous list of tasks; and for " + 
            "saving your list of tasks when this session terminates <3");
      }
    } catch (IOException e) {
      throw new DukeException("Honey! There was a problem with creating or obtaining " + 
          "your list of tasks at " + filePath + " ! :(");
    }
  }
}
