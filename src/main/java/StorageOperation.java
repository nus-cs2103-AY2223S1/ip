import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StorageOperation {
    public static final String PATH_TO_DATA_DIRECTORY = "./data/";

    public static void writeTaskListToStorage(TaskList taskList) {
        String taskListFileName = "duke.txt";
        try {
            File directory = new File(PATH_TO_DATA_DIRECTORY);
            if (!directory.exists() || !directory.isDirectory()) {
                directory.mkdir();
            }
            File file = new File(PATH_TO_DATA_DIRECTORY + "/" + taskListFileName);
            FileWriter fw = new FileWriter(file);
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            Ui.showError(e);
        }
    }
}
