import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Storage {
  private File file;

  private void createDirectoryIfNotExists() {
    String folderPath = "./data";
    File folder = new File(folderPath);
    System.out.println(folder.exists());
    if (!folder.exists()) {
      System.out.println("Here");
      folder.mkdir();
    }
  }

  private void createFileIfNotExists() {
    String filePath = "./data/tasks.txt";
    File tasks = new File(filePath);
    if (!tasks.exists()) {
      try {
        tasks.createNewFile();
      } catch (IOException exception) {
        System.out.printf("Failed to create file: %s\n", exception.toString());
      }
    }
    this.file = tasks;
  }

  public void writeTasksToFile(List<Task> tasks) {
    try {
      FileWriter writer = new FileWriter(this.file);
      for (int i = 0; i < tasks.size(); i++) {
        writer.write(String.format("%s\n", tasks.get(i).toFileFormat()));
      }
      writer.close();
    } catch (IOException exception) {
      System.out.printf("Error occured when writing to file: %s\n", exception.toString());
    }
  }   

  public Storage() {
    this.createDirectoryIfNotExists();
    this.createFileIfNotExists();
  }
}
