package caca;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import caca.exceptions.InvalidDateException;
import caca.tasks.Deadline;
import caca.tasks.Event;
import caca.tasks.Task;
import caca.tasks.Todo;

/**
 * This class represents the storage system to save and read tasks from the hard disk.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Storage {

    /**
     * A string representing the directory path.
     */
    private final String dirPath;

    /**
     * A string representing the file path.
     */
    private final String filePath;

    /**
     * Constructor for creating a Storage.
     *
     * @param dirPath Directory path of file being read.
     * @param filePath Path of the file being read.
     */
    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    /**
     * Either displays all tasks stored in file (when applicable) as soon as the program starts,
     * or prompts user if file does not exist.
     *
     * @throws FileNotFoundException If file is not found.
     */
    public void loadFile() throws FileNotFoundException {
        String line = "____________________________________________________________\n";

        File file = new File(filePath);

        if (file.exists()) {

            String openFileMsg = "Opening the file that stores all your tasks...\n\n";
            String showTaskmsg = "These are the tasks in your list:";
            System.out.println(openFileMsg + showTaskmsg);

            // Prints all tasks found in the file.
            Scanner sc = new Scanner(file);
            if (!sc.hasNextLine()) {
                System.out.println("There is no task in your list.");

            } else {
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    System.out.println(task);
                }
            }

            // Prints a line to end CaCa loading response.
            System.out.println(line);

        } else {
            System.out.println("The file to store all your tasks does not exist.\n"
                    + "I will create one now...\n"
                    + line);
        }
    }

    /**
     * Creates a file if file does not exist in hard disk.
     *
     * @param dirPath Directory path of created file.
     * @param filePath File path of created file.
     * @return The file created.
     * @throws IOException If there exists failed or interrupted I/O operations.
     */
    public File createFile(String dirPath, String filePath) throws IOException {
        File directory = new File(dirPath);
        File file = new File(filePath);
        directory.mkdir();
        file.createNewFile();
        return file;
    }

    /**
     * Updates tasks in file.
     *
     * @param tasks List of tasks in file.
     * @throws IOException If there exists failed or interrupted I/O operations.
     */
    public void updateFile(TaskList tasks) throws IOException {
        // Solution below adapted from W3.4c C++ to Java -> Miscellaneous Topics -> File access
        // from https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/topics.html#W3-4
        // and https://github.com/cheehongw/ip/blob/master/src/main/java/duke/Storage.java
        String newFilePath = filePath + ".new";
        File file = createFile(dirPath, newFilePath);
        FileWriter fw = new FileWriter(file, true);
        for (Task task : tasks.getTasks()) {
            fw.write(task.toFileFormat() + "\n");
        }
        fw.close();

        // Solution below adapted from https://docs.oracle.com/javase/tutorial/essential/io/move.html
        // and https://stackoverflow.com/questions/27749755/
        // files-move-replace-existing-cannot-be-resolved-to-a-variable
        Files.move(Paths.get(newFilePath), Paths.get(filePath), REPLACE_EXISTING);
    }

    /**
     * Reads tasks stored in file.
     *
     * @return A list of all the tasks stored.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    public TaskList readFile() throws InvalidDateException {
        TaskList tasks = new TaskList(null);
        File file = new File(filePath);

        // Surround with try-catch block as suggested by IDE.
        Scanner sc = null;
        try {
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String task = sc.nextLine();

                // Solution below adapted from
                // https://stackoverflow.com/questions/10796160/
                // splitting-a-java-string-by-the-pipe-symbol-using-split
                // and https://github.com/cheehongw/ip/blob/master/src/main/java/duke/Storage.java
                String[] taskDetails = task.split(" \\| ");
                String taskType = taskDetails[0];
                String statusIcon = taskDetails[1];
                boolean isDone = statusIcon.equals("X");
                String description = taskDetails[2];

                // Stores all the tasks from file into the array.
                switch (taskType) {
                case "T":
                    tasks.addTask(new Todo(description, isDone));
                    break;
                case "D":
                    String by = taskDetails[3];
                    tasks.addTask(new Deadline(description, by, isDone));
                    break;
                case "E":
                    String at = taskDetails[3];
                    tasks.addTask(new Event(description, at, isDone));
                    break;
                default:
                    break;
                }

            }

        } catch (FileNotFoundException e) {
            // Creates a new list to store subsequent tasks entered by user
            // if data file does not exist at the start.
            tasks = new TaskList(null);

        } finally {
            // Prevents NullPointerException as suggested by IDE.
            if (sc != null) {
                sc.close();
            }
        }

        return tasks;
    }

}
