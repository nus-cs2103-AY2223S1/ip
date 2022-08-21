import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StorageOperation {
    public static final String PATH_TO_DATA_DIRECTORY = "./data/";
    public static final String TASK_LIST_STORAGE_NAME = "duke.txt";

    public static void writeTaskListToStorage(TaskList taskList) throws DukeException {
        try {
            File directory = new File(PATH_TO_DATA_DIRECTORY);
            if (!directory.exists() || !directory.isDirectory()) {
                directory.mkdir();
            }
            File file = new File(PATH_TO_DATA_DIRECTORY + "/" + TASK_LIST_STORAGE_NAME);
            FileWriter fw = new FileWriter(file);
            StringBuilder toWrite = new StringBuilder();
            for (String taskString : taskList.getAllTasksInStorageFormat()) {
                toWrite.append(taskString + System.lineSeparator());
            }
            fw.write(toWrite.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to storage file");
        }
    }

    public static void readStorageToTaskList(TaskList taskList) throws DukeException {
        try {
            File file = new File(PATH_TO_DATA_DIRECTORY + "/" + TASK_LIST_STORAGE_NAME);
            if (!file.exists()) {
                throw new DukeException("Storage file does not exist yet");
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                taskList.addTask(StorageParser.parseTaskString(taskString));
            }

        } catch (IOException e) {
            throw new DukeException("Error reading from storage file");
        }
    }
}
