package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Handles loading and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new directory/file if it does not exist.
     * @param filePath directory of where tasks are saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        String[] pathSegment = filePath.split("/");
        try {
            File storageFolder = new File(pathSegment[0]);
            File storageFile = new File(this.filePath);

            //make a data folder if it does not exist
            if (!storageFolder.exists()) {
                storageFolder.mkdir();
            }
            //create task.txt if it does not exist
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }

        } catch (IOException error) {
            System.out.println("error finding:" + error.getMessage());
        }
    }

    /**
     * Loads saved task from data.txt to a list.
     * @return previously saved tasks.
     * @throws DukeException If error loading file.
     */
    public List<Task> load() throws DukeException {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            BufferedReader storageReader = new BufferedReader(new FileReader(this.filePath));
            String savedTaskString = storageReader.readLine();

            while (savedTaskString != null) {
                String[] taskSegment = savedTaskString.split("\\|");
                String taskType = taskSegment[0];
                String taskStatus = taskSegment[1];
                String taskDescription = taskSegment[2];
                String taskDate = null;
                LocalDate taskLocalDate = null;
                if (taskSegment.length >= 4) {
                    if (taskType.equals("D")) {
                        try {
                            taskLocalDate = LocalDate.parse(taskSegment[3]);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        taskDate = taskSegment[3];
                    }
                }

                Task currentSavedTask = null;
                switch(taskType) {
                case "T":
                    currentSavedTask = new Todo(taskDescription);
                    break;
                case "D":
                    currentSavedTask = new Deadline(taskDescription, taskLocalDate);
                    break;
                case "E":
                    currentSavedTask = new Event(taskDescription, taskDate);
                    break;
                default:
                    break;
                }

                if (taskStatus.equals("X")) {
                    currentSavedTask.markAsDone();
                }

                loadedTasks.add(currentSavedTask);
                savedTaskString = storageReader.readLine();
            }
            storageReader.close();
        } catch (IOException error) {
            System.out.println("error loading:" + error.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Saves all existing tasks into data.txt.
     * @param taskToSave list of tasks to be saved.
     */
    public void saveTasksInStorage(List<Task> taskToSave) {
        try {
            FileWriter storageWriter = new FileWriter(this.filePath);
            for (Task task : taskToSave) {
                storageWriter.write(task.toStringForStorage() + "\n");
            }
            storageWriter.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }
}
