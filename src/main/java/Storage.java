import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * This class represents the storage system to save and read tasks from the hard disk.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Storage {

    /**
     * A string representing the directory path.
     */
    private static final String DIR_PATH = "data/";

    /**
     * A string representing the file path.
     */
    private static final String FILE_PATH = "data/caca.txt";

    /**
     * Either displays all tasks stored in file (when applicable),
     * or prompts user if file does not exist.
     * @throws FileNotFoundException If file is not found.
     */
    public static void loadFile() throws FileNotFoundException {
        String LINE = "____________________________________________________________\n";

        File file = new File(FILE_PATH);

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
            System.out.println(LINE);

        } else {
            System.out.println("The file to store all your tasks does not exist.\n"
                    + "I will create one now...\n"
                    + LINE);
        }
    }

    /**
     * Creates a file if file does not exist in hard disk.
     * @param dirPath Directory path of created file.
     * @param filePath File path of created file.
     * @return The file created.
     * @throws IOException If there exists failed or interrupted I/O operations.
     */
    public static File createFile(String dirPath, String filePath) throws IOException {
        File directory = new File(dirPath);
        File file = new File(filePath);
        directory.mkdir();
        file.createNewFile();
        return file;
    }

    /**
     * Updates tasks in file.
     * @param tasks List of tasks in file.
     * @throws IOException If there exists failed or interrupted I/O operations.
     */
    public static void updateFile(List<Task> tasks) throws IOException {
        String newFilePath = FILE_PATH + ".new";
        File file = createFile(DIR_PATH, newFilePath);
        FileWriter fw = new FileWriter(file, true);
        for (Task task : tasks) {
            fw.write(task.toFileFormat() + "\n");
        }
        fw.close();

        // Solution below adapted from https://docs.oracle.com/javase/tutorial/essential/io/move.html and
        // https://stackoverflow.com/questions/27749755/files-move-replace-existing-cannot-be-resolved-to-a-variable
        Files.move(Paths.get(newFilePath), Paths.get(FILE_PATH), REPLACE_EXISTING);
    }

    /**
     * Reads tasks stored in file.
     * @return A list of all the tasks stored.
     */
    public static List<Task> readFile() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Surround with try-catch block as suggested by IDE.
        Scanner sc = null;
        try {
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String task = sc.nextLine();

                // Adapted from
                // https://stackoverflow.com/questions/10796160/splitting-a-java-string-by-the-pipe-symbol-using-split
                String[] taskDetails = task.split(" \\| ");
                String taskType = taskDetails[0];
                String statusIcon = taskDetails[1];
                boolean isDone = statusIcon.equals("X");
                String description = taskDetails[2];

                // Stores all the tasks from file into the array.
                switch (taskType) {
                case "T":
                    tasks.add(new Todo(description, isDone));
                    break;
                case "D":
                    String by = taskDetails[3];
                    tasks.add(new Deadline(description, by, isDone));
                    break;
                case "E":
                    String at = taskDetails[3];
                    tasks.add(new Event(description, at, isDone));
                    break;
                default:
                    break;
                }

            }

        } catch (FileNotFoundException e) {
            // Creates a new list to store subsequent tasks entered by user
            // if data file does not exist at the start.
            tasks = new ArrayList<>();

        } finally {
            // Prevents NullPointerException as suggested by IDE.
            if (sc != null) {
                sc.close();
            }
        }

        return tasks;
    }

}
