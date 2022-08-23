import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

  private String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public List<Task> load(){
    File file = new File(filePath);
      String absPath = file.getPath();
      if (!file.exists()) {
        file = new File(absPath, "tasks.txt");
      }
    Scanner sc = null;
    try {
      sc = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    }
    List<Task> taskList = new ArrayList<>();

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] splitStr = line.split("\\|");
        String taskType = splitStr[0];
        boolean isDone = splitStr[1] == "1"? true : false;
        String description = splitStr[2];
        String date = "";
        if (splitStr.length > 3) {
          date = splitStr[3];
        }
        switch (taskType) {
        case "T":
          taskList.add(new ToDo(description,isDone));
          break;
        case "D":
          taskList.add(new Deadline(description,date,isDone));
          break;
        case "E":
          taskList.add(new Event(description,date,isDone));
          break;
        default:
          break;
        }
      }
      return taskList;
  }

  public void save(List<Task> taskList) throws IOException {
    FileWriter fw = new FileWriter(filePath);
    try {
      for (int i = 0; i < taskList.size(); i++) {
        Task task = taskList.get(i);
        String isDone = task.isDone? "1" : "0";
        String str = "";
        if (task instanceof ToDo) {
          fw.write("T|" + isDone + "|" + task.description);
        } else if (task instanceof Deadline) {
          fw.write("D|" + isDone + "|" + task.description + "|" + ((Deadline) task).by);
        } else if (task instanceof Event) {
          fw.write("E|" + isDone + "|" + task.description + "|" + ((Event) task).at);
        }
      }
    } catch (IOException e) {
      System.out.println("Unable to save: " + e.getMessage());
    }
  }
}
