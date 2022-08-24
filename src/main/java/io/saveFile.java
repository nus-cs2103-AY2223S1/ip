package io;

import tasks.Task;
import exceptions.DukeException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Assists with the saving of a <code>Task[]</code> to a txt format.
 *
 * @author Kang Wei
 */
public class saveFile {

  /**
   * Writes a <code>Task[]</code> to a txt file.
   *
   * @param tasks The <code>Task[]</code> to write.
   * @param fileLoc The location of the file to write to.
   * @throws DukeExceception Throws a DukeException if there is an IOException
   * during the filewriting process.
   */
  public static void save(ArrayList<Task> tasks, String fileLoc) throws DukeException {
    try {
      FileWriter writer = new FileWriter(fileLoc);
      for (Task task : tasks) {
        writer.write(task.toString());
        writer.write(System.getProperty("line.separator"));
      }
      writer.close();
      System.out.println("Mumbot: Your list of tasks has been successfully saved to " +
          fileLoc + " , hunbun~!\n");
    } catch (IOException e) {
      throw new DukeException("Honey! There was a problem with saving your list of " + 
          "tasks to " + fileLoc + " ! :(\n");
    }
  }
}
