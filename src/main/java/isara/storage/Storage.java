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
    protected final String directory = System.getProperty("user.home") + "/DukeData";
    protected final String filePath = directory + "/Duke.txt";

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

            File dukeFile = new File(filePath);

            if (!dukeFile.exists()) {
                dukeFile.createNewFile();
            }

            return dukeFile;

        } catch (IOException e) {
            throw new IsaraException("OOPS!! A new file cannot be created.");
        }
    }

    /**
     * Reads the duke.txt file (if it already exists).
     *
     * @param dukeFile The file to be read.
     * @return The tasks fetched from the file.
     * @throws IsaraException An exception will be thrown if the bot fails to
     *     read the set of tasks.
     */
    public ArrayList<Task> readFile(File dukeFile) throws IsaraException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(dukeFile);
            while (sc.hasNextLine()) {
                String commands = sc.nextLine();
                String typeOfTask = commands.substring(1, 2);
                String marked = commands.substring(4, 5);
                String description = commands.substring(7);

                switch (typeOfTask) {
                case "T":
                    Task toDo = new ToDo(description);
                    if (marked.equals("X")) {
                        toDo.markAsDone();
                    }
                    tasks.add(toDo);
                    break;
                case "D":
                    String[] deadlineDescription = description.split("by:");
                    String deadlineName = deadlineDescription[0]
                            .substring(0, deadlineDescription[0].length() - 1)
                            .trim();
                    String by = deadlineDescription[1].substring(1, deadlineDescription[1].length() - 1).trim();
                    LocalDate deadlineDate = LocalDate.parse(by);
                    Task deadline = new Deadline(deadlineName, deadlineDate);
                    if (marked.equals("X")) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    String[] eventDescription = description.split("at:");
                    String eventName = eventDescription[0]
                            .substring(0, eventDescription[0].length() - 1)
                            .trim();
                    String at = eventDescription[1].substring(0, eventDescription[1].length() - 1).trim();
                    Task event = new Event(eventName, at);
                    if (marked.equals("X")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    break;
                }
            }
            return tasks;

        } catch (IOException e) {
            throw new IsaraException("OOPS!! Failed to read file.");
        }
    }

    /**
     * Writes and saves the user's list of tasks.
     *
     * @param dukeFile The file to write into.
     * @param tasks The tasks to be saved.
     * @throws IsaraException An exception is thrown if the bot fails to
     *     write into the file.
     */
    public void writeAndSaveToFile(File dukeFile, TaskList tasks) throws IsaraException {
        try {
            FileWriter writer = new FileWriter(dukeFile);
            int numberOfTasks = tasks.getNumberOfTasks();
            for (int i = 0; i < numberOfTasks; i++) {
                Task task = tasks.getTask(i);
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IsaraException("OOPS!! Failed to write to file.");
        }
    }
}
