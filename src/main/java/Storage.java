// Storage: deals with loading tasks from the file and saving tasks in the file
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(Task task) throws IOException {
        String textToAdd = task.formatTaskBeforeSave();
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }

}
