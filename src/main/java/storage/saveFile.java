package storage;

import task.Task;
import exception.DukeException;
import ui.Ui;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Assists with the saving of a <code>Task[]</code> to a txt format.
 * Note that this class is package-private.
 *
 * @author Kang Wei
 */
class saveFile {

  /**
   * Writes a <code>Task[]</code> to a txt file.
   *
   * @param tasks The <code>Task[]</code> to write.
   * @param filePath The location of the file to write to.
   * @throws DukeExceception Throws a DukeException if there is an IOException
   * during the filewriting process.
   */
  public static void save(ArrayList<Task> tasks, String filePath) throws DukeException {
    try {
      FileWriter writer = new FileWriter(filePath);
      for (Task task : tasks) {
        writer.write(task.toString());
        writer.write(System.getProperty("line.separator"));
      }
      writer.close();
      Ui.print("Mumbot: Your list of tasks has been successfully saved to " +
          filePath + " , hunbun~!");
    } catch (IOException e) {
      throw new DukeException("Honey! There was a problem with saving your list of " + 
          "tasks to " + filePath + " ! :(");
    }
  }
}
