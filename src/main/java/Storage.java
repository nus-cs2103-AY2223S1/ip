import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
  private static final String FILEPATH = "./data/duke.txt";
  private static final String SEPARATOR = ",";

  private File file;

  public Storage(String filePath) {
    this.file = new File(filePath);
  }

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

  public void saveFile(ArrayList<Task> tasks) throws DukeException {
    createFile();
    PrintWriter writer;
    try {
      writer = new PrintWriter(file);
    } catch (FileNotFoundException e) {
      throw new DukeException("File not found");
    } catch (Exception e) {
      throw new DukeException("Exception. Don't know the specific type");
    }
    for (Task task : tasks) {
      String[] data = task.getPrintRepresentation();
      writer.println(Arrays.stream(data).reduce("", (x, y) -> x + SEPARATOR + y).substring(1));
    }
    writer.close();
  }

  public ArrayList<Task> readFile() throws DukeException {
    createFile();
    ArrayList<Task> tasks = new ArrayList<>();
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
          tasks.add(task);
          break;
        }
        case "Event": {
          Task task = new Event(strArray[1], Boolean.parseBoolean(strArray[2]), strArray[3]);
          tasks.add(task);
          break;
        }
        case "Deadline": {
          Task task = new Deadline(strArray[1], Boolean.parseBoolean(strArray[2]), strArray[3]);
          tasks.add(task);
          break;
        }
        default:
        }
      }
      reader.close();
    } catch (Exception e) {
      throw new DukeException("Error");
    }
    return tasks;
  }
}
