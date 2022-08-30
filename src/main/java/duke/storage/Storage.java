package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a storage to load and save all <code>Task</code> in the list
 * of tasks to a file.
 *
 * @author Derrick Khoo
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a storage to load and save all <code>Task</code> in the list
     * of tasks to a file.
     *
     * @param filePath the path of the file that stores the data in the hard disk
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves all the <code>Task</code> to a file in the hard disk.
     *
     * @param taskList the list of tasks
     * @throws DukeException if there is an error saving the list
     *                       of tasks to the hard drive.
     */
    public void saveData(TaskList taskList) throws DukeException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        String filePath = "data/duke.txt";
        File data = new File(filePath);
        // Write to data
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            List<Task> listOfTasks = taskList.getTaskList();
            for (Task t : listOfTasks) {
                fileWriter.write(t.toFileDescription() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Hey! Are you in the wrong directory? You are currently at"
                    + e.getMessage());
        }
    }

    /**
     * Loads all the <code>Task</code> saved in the file located in the hard disk.
     *
     * @return the list of <code>Task</code>
     * @throws DukeException if there is an error loading the tasks from the file
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        String filePath = "data/duke.txt";
        File data = new File(filePath);
        // Load the file data into corresponding ArrayList
        try {
            if (data.exists()) {
                Scanner scanner = new Scanner(data);
                while (scanner.hasNext()) {
                    String currentlyAt = scanner.nextLine();
                    char first = currentlyAt.charAt(0);
                    Task task;
                    switch(first) {
                    case 'T':
                        task = Todo.fromFileDescription(currentlyAt);
                        break;
                    case 'D':
                        try {
                            task = Deadline.fromFileDescription(currentlyAt);
                        } catch (DukeException e) {
                            throw e;
                        }
                        break;
                    case 'E':
                        try {
                            task = Event.fromFileDescription(currentlyAt);
                        } catch (DukeException e) {
                            throw e;
                        }
                        break;
                    default:
                        throw new DukeException("What!? How did this happen... I'm pretty sure you"
                                + "have an itchy hand and modified the duke.txt file!!!");
                    }
                    list.add(task);
                }
            } else {
                data.createNewFile();
                throw new DukeException("Welcome! This is your first time using Duke right?");
            }
        } catch (IOException e) {
            throw new DukeException("Are you a hacker? How on earth did you get to this stage!?");
        }
        return list;
    }
}
