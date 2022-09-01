package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Storage implements method for storing and fetching the task list from hard drive.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class Storage {

    /** Default file path used if the user does not provide the file name. */
    private static final String path = Paths.get(".").toAbsolutePath().toString();
    private static final String RELATIVE_FILEPATH = path.substring(0, path.length() - 1) + "src/main/";

    private String filePath = "";

    /**
     * Saves file to a default path if one was not provided by the user.
     */
    public Storage() {
        this.filePath = RELATIVE_FILEPATH + "data/tasks.txt";
    }

    /**
     * Saves the file to the file path specified by the user.
     *
     * @param filePath path for the file to be stored
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Storage(String filePath) {
        try {
            this.filePath = RELATIVE_FILEPATH + filePath;
            File file = new File(this.filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores the given task list to the given path.
     *
     * @param toStore the task list to be stored
     */
    public void store(TaskList toStore) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(toStore);
            for (String task : encodedTaskList) {
                writer.write("\n" + task);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Loads the stored task list from the specified file path.
     *
     * @return an ArrayList<Task> to be passed as parameters for the TaskList constructor
     */
    public ArrayList<Task> load() {
        ArrayList<Task> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            List<String> encodedTaskList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                encodedTaskList.add(line);
            }
            reader.close();
            result = TaskListDecoder.decodeTaskList(encodedTaskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
