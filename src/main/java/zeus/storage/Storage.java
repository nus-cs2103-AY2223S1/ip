package zeus.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import zeus.ZeusException;
import zeus.task.Deadline;
import zeus.task.Event;
import zeus.task.Task;
import zeus.task.TaskList;
import zeus.task.Todo;

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
     * @throws ZeusException if there is an error saving the list
     *                       of tasks to the hard drive.
     */
    public void saveData(TaskList taskList) throws ZeusException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            List<Task> listOfTasks = taskList.getTaskList();
            for (Task t : listOfTasks) {
                fileWriter.write(t.toFileDescription() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new ZeusException("Hey! Are you in the wrong directory? You are currently at "
                    + e.getMessage());
        }
    }

    /**
     * Loads all the <code>Task</code> saved in the file located in the hard disk.
     *
     * @return the list of <code>Task</code>
     * @throws ZeusException if there is an error loading the tasks from the file
     */
    public ArrayList<Task> loadData() throws ZeusException {
        ArrayList<Task> list = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            File data = new File(filePath);
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
                        } catch (ZeusException e) {
                            throw e;
                        }
                        break;
                    case 'E':
                        try {
                            task = Event.fromFileDescription(currentlyAt);
                        } catch (ZeusException e) {
                            throw e;
                        }
                        break;
                    default:
                        throw new ZeusException("What!? How did this happen... I'm pretty sure you"
                                + "have an itchy hand and modified the zeus.txt file!!!");
                    }
                    list.add(task);
                }
            } else {
                data.createNewFile();
                throw new ZeusException("Welcome! This is your first time using Zeus right?");
            }
        } catch (IOException e) {
            throw new ZeusException("Are you a hacker? How on earth did you get to this stage!?");
        }
        return list;
    }
}
