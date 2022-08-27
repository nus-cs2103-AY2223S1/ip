package components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents storage, which stores user's inputted tasks in a text file.
 */
public class Storage {

  private File f;
  private ArrayList<Task> ls;

  public Storage(String filepath) {
    ls = new ArrayList<Task>();
    f = new File(filepath);
  }

  /**
   * Writes user's inputted tasks into a text file.
   *
   * @param filePath  path of text file to br written onto.
   * @param textToAdd text to be written onto text file.
   * @throws IOException If file is not to be found.
   */
  public void writeToFile(String filePath, String textToAdd) throws IOException {
    FileWriter fw = new FileWriter(filePath, true);
    fw.write(textToAdd);
    fw.close();
  }

  /**
   * deletes user's inputted tasks from a text file.
   *
   * @param filePath path of text file to br deleted from.
   * @param iterate  ArrayList of tasks that are not deleted.
   * @throws IOException If file is not to be found.
   */
  public void deleteFromFile(String filePath, ArrayList<Task> iterate) throws IOException {
    FileWriter fw = new FileWriter("temp.txt", true);
    File f = new File("temp.txt");
    for (int i = 0; i < iterate.size(); i++) {
      fw.write(iterate.get(i).toString() + System.lineSeparator());
    }
    fw.close();
    Files.delete(Paths.get(filePath));
    File g = new File(filePath);
    f.renameTo(g);

  }

  /**
   * add tasks into an ArrayList.
   *
   * @param task task to be added into ArrayList.
   */
  public void add(Task task) {
    ls.add(task);
  }

  /**
   * load tasks into an ArrayList.
   *
   * @returns ArrayList of tasks.
   * @throws DukeException If text file is not found
   */
  public ArrayList<Task> load() throws DukeException {
    try {
      Scanner s = new Scanner(f);
      while (s.hasNext()) {
        String line = s.nextLine();
        Parser.parseLineStorage(line, this);

      }
      s.close();
      return ls;
    } catch (FileNotFoundException e) {
      throw new DukeException("duke.txt is not found!");
    }

  }
}
