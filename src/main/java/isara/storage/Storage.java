package isara.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.task.Deadline;
import isara.task.Event;
import isara.task.Task;
import isara.task.ToDo;

/**
 * Class that represents the storage in which
 * the data from Duke will store into.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Storage {
    protected final String directory = System.getProperty("user.home") + "/Isara";
    protected final String filePath = directory + "/isara.txt";

    /**
     * Initializes a file that the bot will write into to save the tasks.
     *
     * @return The file that the bot will write into.
     * @throws IsaraException An exception that will be thrown if the file cannot
     *     be initialized.
     */
    public File initialize() throws IsaraException {
        try {
            File parentDirectory = new File(directory);

            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }

            File isaraFile = new File(filePath);

            if (!isaraFile.exists()) {
                isaraFile.createNewFile();
            }

            return isaraFile;

        } catch (IOException e) {
            throw new IsaraException("OOPS!! A new file cannot be created.");
        }
    }

    /**
     * Adds ToDo task to the ArrayList after reading
     * from the file.
     *
     * @param tasks The task list to add to.
     * @param description The description of the task.
     * @param marked The status of the task, whether it is marked as done or not.
     */
    public void addToDo(ArrayList<Task> tasks, String description, String marked) {
        Task toDo = new ToDo(description);
        if (marked.equals("X")) {
            toDo.mark();
        }
        tasks.add(toDo);
    }

    /**
     * Adds Deadline task to the ArrayList after reading
     * from the file.
     *
     * @param tasks The task list to add to.
     * @param description The description of the task.
     * @param marked The status of the task, whether it is marked as done or not.
     */
    public void addDeadline(ArrayList<Task> tasks, String description, String marked) {
        String[] deadlineDescription = description.split("by:");
        String deadlineName = deadlineDescription[0]
                .substring(0, deadlineDescription[0].length() - 1)
                .trim();
        String by = deadlineDescription[1].substring(1, deadlineDescription[1].length() - 1).trim();
        LocalDate deadlineDate = LocalDate.parse(by);
        Task deadline = new Deadline(deadlineName, deadlineDate);
        if (marked.equals("X")) {
            deadline.mark();
        }
        tasks.add(deadline);
    }

    /**
     * Adds Event task to the ArrayList after reading
     * from the file.
     *
     * @param tasks The task list to add to.
     * @param description The description of the task.
     * @param marked The status of the task, whether it is marked as done or not.
     */
    public void addEvent(ArrayList<Task> tasks, String description, String marked) {
        String[] eventDescription = description.split("at:");
        String eventName = eventDescription[0]
                .substring(0, eventDescription[0].length() - 1)
                .trim();
        String at = eventDescription[1].substring(0, eventDescription[1].length() - 1).trim();
        Task event = new Event(eventName, at);
        if (marked.equals("X")) {
            event.mark();
        }
        tasks.add(event);
    }

    /**
     * Reads the isara.txt file (if it already exists).
     *
     * @param isaraFile The file to be read.
     * @return The tasks fetched from the file.
     * @throws IsaraException An exception will be thrown if the bot fails to
     *     read the set of tasks.
     */
    public ArrayList<Task> readFile(File isaraFile) throws IsaraException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(isaraFile);
            while (sc.hasNextLine()) {
                String commands = sc.nextLine();
                String typeOfTask = commands.substring(1, 2);
                String marked = commands.substring(4, 5);
                String description = commands.substring(7);

                switch (typeOfTask) {
                case "T":
                    addToDo(tasks, description, marked);
                    break;
                case "D":
                    addDeadline(tasks, description, marked);
                    break;
                case "E":
                    addEvent(tasks, description, marked);
                    break;
                default:
                    break;
                }
            }
            return tasks;

        } catch (IOException e) {
            throw new IsaraException("☹ OOPS!!! Failed to read file.");
        }
    }

    /**
     * Writes and saves the user's list of tasks.
     *
     * @param isaraFile The file to write into.
     * @param tasks The tasks to be saved.
     * @throws IsaraException An exception is thrown if the bot fails to
     *     write into the file.
     */
    public void writeAndSaveToFile(File isaraFile, TaskList tasks) throws IsaraException {
        try {
            FileWriter writer = new FileWriter(isaraFile);
            int numberOfTasks = tasks.getNumberOfTasks();
            for (int i = 0; i < numberOfTasks; i++) {
                Task task = tasks.getTask(i);
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IsaraException("☹ OOPS!!! Failed to write to file.");
        }
    }
}
