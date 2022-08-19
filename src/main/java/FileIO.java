import java.io.*;

public class FileIO {
  /**
   * Save the list of tasks to a file
   * @param list List of tasks to save
   * @param filename The path of the file to save to
   */
  public static void save(StorageList list, String filename) {
    try {
      FileWriter fw = new FileWriter(filename);
      BufferedWriter bw = new BufferedWriter(fw);
      for (int i = 0; i < list.getSize(); i++) {
        bw.write(list.get(i).toString());
        bw.newLine();
      }
      bw.close();
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(new DukeException("Error writing to file.").getMessage());
    }
  }

  /**
   * Loads the tasks from the file
   * @param list List of tasks to load into
   * @param filename The path of the file to load from
   */
  public static void load(StorageList list, String filename) {
    try {
      list.reset();
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        if (!line.equals("")) {
          parseLine(line, list);
        }
      }
      br.close();
    } catch (IOException e) {
      System.out.println(new DukeException("Error writing to file.").getMessage());
    }
  }

  /**
   * Parses a line of text and adds the task to the list
   * @param line The line of text to parse
   * @param list The list to add the task to
   */
  private static void parseLine(String line, StorageList list) {
    String description = getDescription(line);
    String addition = getAddition(line);
    Task task;
    switch (line.charAt(1)) {
      case 'D':
        task = new Deadline(description, addition);
        break;
      case 'E':
        task = new Event(description, addition);
        break;
      case 'T':
        task = new Todo(description);
        break;
      default:
        task = new Task(description); 
    }
    list.add(task);
  }

  /**
   * Returns the description of the task
   * @param line The line of text to parse
   * @return The description of the task
   */
  private static String getDescription(String line) {
    if (line.indexOf(':') == -1) {
      return line.substring(7);
    } else {
      return line.substring(7, line.indexOf(" (") + 1);
    }
  }

  /**
   * Returns the additional information of the task
   * @param line The line of text to parse
   * @return The additional information of the task
   */
  private static String getAddition(String line) {
    if (line.indexOf(':') == -1) {
      return "";
    } else {
      return line.substring(line.indexOf(":") + 2, line.indexOf(")"));
    }
  }
}
