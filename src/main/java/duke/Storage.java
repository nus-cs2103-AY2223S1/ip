package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * The Storage class deals with loading tasks from the data file and
 * saving tasks in the file.
 *
 * @author Edric Yeo
 */
public class Storage {

    private String filePath;

    /**
     * Creates a Storage instance based on a given data file.
     *
     * @param filePath The filePath of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        // Creates a storage folder and file if not present
        try {
            String[] splitFilePath = filePath.split("/");
            File storageFolder = new File(splitFilePath[0]);
            File storageFile = new File(filePath);
            if (!storageFolder.exists()) {
                storageFolder.mkdir();
            }
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException error) {
            System.out.println("Error creating new storage file!");
        }
    }

    /**
     * Returns the list of tasks from the saved data file.
     *
     * @return A List of Tasks that have been stored in the data file.
     * @throws FileNotFoundException if unable to read from data file.
     */
    public List<Task> loadTasks() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = input.split(" # ");

            // Checks description of task
            String desc = words[2];
            // Checks type of task
            String typeOfTask = words[0];
            if (typeOfTask.equals("T")) {
                Task todo = new Todo(desc);
                // Checks if task is marked
                if (words[1].equals("1")) {
                    todo.mark();
                }
                tasks.add(todo);
            } else if (typeOfTask.equals("D")) {
                Task deadline = new Deadline(desc, words[3]);
                if (words[1].equals("1")) {
                    deadline.mark();
                }
                tasks.add(deadline);
            } else if (typeOfTask.equals("E")) {
                Task event = new Event(desc, words[3]);
                if (words[1].equals("1")) {
                    event.mark();
                }
                tasks.add(event);
            } else {
                System.out.println("Incorrect File Input!");
            }
        }
        return tasks;
    }

    /**
     * Writes the current tasks into the data file.
     *
     * @param tasks The current List of Tasks.
     * @throws IOException if unable to save to data file.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        tasks.saveTasks(this.filePath);
    }
}
