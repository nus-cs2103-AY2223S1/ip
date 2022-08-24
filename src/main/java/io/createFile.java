package io;

import java.io.File;
import java.io.IOException;

import exception.DukeException;
import ui.Ui;

/**
 * Creates a txt file that should eventually be used for
 * the writing of a <code>Task[]</code> - in String format - to itself.
 *
 * @author Kang Wei
 */
public class createFile {

  /**
   * Creates a file in the specified location.
   *
   * @param fileLoc The location that the file should be created in.
   * @throws DukeException Throws a DukeException if an IOException is caught.
   */
  public static void makeFile(String fileLoc) throws DukeException {
    try {
      File file = new File(fileLoc);
      if (file.createNewFile()) {
        Ui.print("Mumbot: A .txt file has been created in " +
            fileLoc + " , which will contain your list of tasks once this session " +
            "terminates <3");
      } else {
        Ui.print("Mumbot: A .txt file already existing in " +
            fileLoc + " will be used for loading your previous list of tasks; and for " + 
            "saving your list of tasks when this session terminates <3");
      }
    } catch (IOException e) {
      throw new DukeException("Honey! There was a problem with creating or obtaining " + 
          "your list of tasks at " + fileLoc + " ! :(");
    }
  }
}
