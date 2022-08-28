package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the Storage object responsible for loading and saving tasks during a Duke session.
 *
 * @author njxue
 * @version v0.1
 */
public class Storage {
    /** Path object representing the relative path to the file to load and save the tasks. **/
    private final Path path;

    /** String representation of the relative path to the file to load and save the tasks. **/
    private final String pathString;

    /**
     * Creates a new Storage object.
     *
     * @param filePath String representation of the relative path to the file to load and save the tasks.
     */
    public Storage(String filePath) {
        this.path = Paths.get(filePath);
        this.pathString = path.toString();
        File parent = new File(new File(pathString).getParent());
        if (!parent.exists()) {
            parent.mkdirs();
        }
    }

    /**
     * Loads the task from the file located in the specified path in path.
     *
     * @return List of previously saved tasks.
     * @throws FileNotFoundException If the file located in path does not exist.
     */
    public List<Task> load() throws FileNotFoundException {
        File file = new File(pathString);
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            Task task = Task.parse(sc.nextLine());
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Saves the task to the file located in the specified path in path.
     *
     * @param tasks TaskList containing the list of tasks to be saved.
     * @throws DukeException If the file located in path does not exist.
     */
    public void save(TaskList tasks) throws DukeException {
        File file = new File(pathString);
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                fw.write(task.toFileFormatString() + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            throw new DukeException("Unable to write to file :(");
        }
    }
}
