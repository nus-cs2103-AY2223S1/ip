package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Represents an encapsulation that deals with loading tasks from the save file
 * and saving tasks in the file
 */
public class Storage {
  private static final String SEPARATOR = ",";

  private File file;

  /**
   * Initialises the storage object.
   *
   * @param filePath Path to the save file
   */
  public Storage(String filePath) {
    this.file = new File(filePath);
  }

  /**
   * A method to create file that also handles the errors.
   */
  public void createFile() throws DukeException {
    if (!file.exists()) {
      try {
        file.getParentFile().mkdir();
        file.createNewFile();
      } catch (IOException e) {
        throw new DukeException("I/O error");
      } catch (SecurityException e) {
        throw new DukeException("Cannot write. No permissions");
      } catch (Exception e) {
        throw new DukeException("Exception. Don't know the specific type");
      }
    }
  }

  /**
   * A method to save TaskList to a file while handling errors occured during the
   * process.
   *
   * @param taskList TaskList
   */
  public void saveFile(TaskList taskList) throws DukeException {
    createFile();
    PrintWriter writer;
    try {
      writer = new PrintWriter(file);
    } catch (FileNotFoundException e) {
      throw new DukeException("File not found");
    } catch (Exception e) {
      throw new DukeException("Exception. Don't know the specific type");
    }
    for (int i = 0; i < taskList.size(); ++i) {
      Task task = taskList.get(i);
      String[] data = task.getPrintRepresentation();
      writer.println(Arrays.stream(data).reduce("", (x, y) -> x + SEPARATOR + y).substring(1));
    }
    writer.close();
  }

  /**
   * A method to read the save file and returns TaskList.
   *
   * @return TaskList where its tasks are those in the save file
   */
  public TaskList readFile() throws DukeException {
    createFile();
    TaskList taskList = new TaskList();
    BufferedReader reader;

    try {
      FileReader fr = new FileReader(this.file);
      reader = new BufferedReader(fr);
      String line;

      while ((line = reader.readLine()) != null) {
        String[] strArray = line.split(",");
        switch (strArray[0]) {
        case "Todo": {
          Task task = new Todo(strArray[1], Boolean.parseBoolean(strArray[2]));
          taskList.add(task);
          break;
        }
        case "Event": {
          Task task = new Event(strArray[1], Boolean.parseBoolean(strArray[2]), strArray[3]);
          taskList.add(task);
          break;
        }
        case "Deadline": {
          Task task = new Deadline(strArray[1], Boolean.parseBoolean(strArray[2]), strArray[3]);
          taskList.add(task);
          break;
        }
        default:
        }
      }
      reader.close();
    } catch (Exception e) {
      throw new DukeException("Error");
    }
    return taskList;
  }
}
