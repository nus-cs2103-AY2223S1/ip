import java.io.File;
import java.io.IOException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load() {
        File folder = new File("Data");
        folder.mkdirs();
        File file = new File(filePath);
        try {
            if (!file.createNewFile()) {
                TaskList.textToTaskList(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
