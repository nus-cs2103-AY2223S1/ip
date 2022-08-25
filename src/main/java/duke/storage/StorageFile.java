package duke.storage;

import duke.exceptions.UnableToSaveException;
import duke.task.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the file used to store the task list.
 */
public class StorageFile {

    public static final String DEFAULT_STORAGE_FILEPATH = "./duke.txt";
    private File file;


    /**
     * Creates a storage file to store todolist user created.
     */
    public StorageFile() {
        try {
            File file = new File(DEFAULT_STORAGE_FILEPATH);
            if (!file.exists()) {
                file.createNewFile();
                this.file = file;
            }
        } catch (IOException e) {
            // FileAlreadyExitsException (under IOException) ignored.
        }
    }


    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @param list todolist data to save
     */
    public void saveList(TaskList list) throws UnableToSaveException {
        BufferedWriter bw;
        try {
            bw = Files.newBufferedWriter(Paths.get(DEFAULT_STORAGE_FILEPATH));
            bw.write("");
            bw.flush();
            bw.write(list.encodeToString());
            bw.close();
        } catch (IOException e) {
            throw new UnableToSaveException();
        }
    }

    /**
     * Loads the {@code TaskList} data from the storage file and return it.
     * Returns an empty arraylist of task if the file does not exist.
     */
    public ArrayList<Task> loadTasks() {
        List<String> content;
        ArrayList<Task> toDoList = new ArrayList<>();
        try {
            content = Files.readAllLines(Paths.get(DEFAULT_STORAGE_FILEPATH));
            String[] inputArray;
            String taskType;
            String taskDescription;
            for (String line : content) {
                inputArray = Arrays.stream(line.split("\\|")).map(String::trim).toArray(String[]::new);
                taskType = inputArray[0];
                taskDescription = inputArray[2];
                Task task = null;
                switch (taskType) {
                    case "T":
                        task = new TodoTask(taskDescription);
                        break;
                    case "D":
                        task = new DeadlineTask(taskDescription, inputArray[3]);
                        break;
                    case "E":
                        task = new EventTask(taskDescription, inputArray[3]);
                        break;
                }

                if (task != null && inputArray[1].equals("Done")) {
                    task.markDone();
                }
                toDoList.add(task);
            }
            return toDoList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return toDoList;
    }
}
