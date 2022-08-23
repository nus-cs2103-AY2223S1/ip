import java.io.File;
import java.io.IOException;

public class Storage {

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
        System.out.printf("Failed to create file %s\n", exception.toString());
      }
    }
  }

  public Storage() {
    this.createDirectoryIfNotExists();
    this.createFileIfNotExists();
  }
}
