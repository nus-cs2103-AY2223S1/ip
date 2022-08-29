package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
/**
 * Represents the duke.storage.Storage class which stores data for duke.Duke.
 *
 * @author Leong Jia Hao Daniel
 */
public class Storage {

    private File currentFile;

    /**
     * The Storage constructor.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.currentFile = new File(filePath);
    }

    /**
     * Loads the ArrayList of tasks from the input file.
     *
     * @return An ArrayList containing the tasks in the input file.
     * @throws DukeException If file not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> temp = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File("data/duke.txt");
        try {
            if (data.exists()) {
                Scanner fileScanner = new Scanner(data);
                while (fileScanner.hasNext()) {
                    String string = fileScanner.nextLine();
                    char taskType = string.charAt(0);
                    try {
                        switch (taskType) {
                        case 'T':
                            ToDo todo = ToDo.parseFile(string);
                            temp.add(todo);
                            break;
                        case 'D':
                            Deadline deadline = Deadline.parseFile(string);
                            temp.add(deadline);
                            break;
                        case 'E':
                            Event event = Event.parseFile(string);
                            temp.add(event);
                            break;
                        default:
                            throw new DukeException("Error in storage!");
                        }
                    } catch (DukeException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                data.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param data The tasklist of data that is meant to
     *             be saved.
     */
    public void saveFile(TaskList data) {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter writer = new FileWriter("data/duke.txt", false);
            for (Task task : data.getTaskList()) {
                writer.write(task.toDataFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
