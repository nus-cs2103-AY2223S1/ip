package violet;

import violet.exception.VioletException;
import violet.task.Deadline;
import violet.task.Event;
import violet.task.Task;
import violet.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** The relative path of the file to be loaded and saved in */
    private String filePath;

    /**
     * Instantiates the Storage object.
     *
     * @param filePath Relative path of the file to be loaded and saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Prints the contents of the file onto the console.
     *
     * @param filePath Relative path of the file to be loaded from.
     * @throws FileNotFoundException If filePath is invalid.
     */
    public static String printFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String response  = "";

        while (scanner.hasNextLine()) {
            response += scanner.nextLine() + "\n";
        }
        return response;
    }

    /**
     * Reformats the content from the saved file into the list.
     *
     * @param file File to be loaded from.
     * @param taskList The collection of Task objects.
     * @throws FileNotFoundException If file does not exist.
     */
    public static void formatToList(File file, ArrayList<Task> taskList) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        try {
            while (scanner.hasNextLine()) {
                String[] taskDescription = scanner.nextLine().split(" \\| ");
                String taskType = taskDescription[0];
                boolean isDone = taskDescription[2].equals("1");
                String description = taskDescription[3] + " ";
                String date = " ";
                Task task = null;

                if (taskDescription.length == 5) {
                    date += taskDescription[4];
                }

                switch (taskType) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    task = new Deadline(description, isDone, date);
                    break;
                case "E":
                    task = new Event(description, isDone, date);
                    break;
                default:
                    throw new VioletException("I don't think I understand what is going on here.");
                }

                if (isDone) {
                    task.markAsDone();
                }
                task.setPriorityStatus(taskDescription[1]);
                taskList.add(task);
            }
        } catch (VioletException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the file when Violet is opened.
     *
     * @return The collection of Tasks that was saved in the file.
     * @throws VioletException If file does not exist.
     */
    public ArrayList<Task> load() throws VioletException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File(this.filePath);

            if (!file.exists()) {
                Path path = Paths.get("./data/");
                Files.createDirectories(path);
                file.createNewFile();
            }

            printFileContents(this.filePath);
            formatToList(file, taskList);
        } catch (FileNotFoundException e) {
            throw new VioletException("Error: Cannot load file!");
        } catch (IOException e) {
            throw new VioletException("Error: File does not exist");
        }
        return taskList;
    }

    /**
     * Converts the tasks in the list to the file format.
     *
     * @param taskList The collection of Task objects.
     * @return The string representation of the tasks in file format.
     */
    public static String formatToFile(TaskList taskList) {
        String result = "";
        for (Task t : taskList.getTaskList()) {
            result += t.fileStatus() + "\n";
        }
        return result;
    }

    /**
     * Saves the list into the file when Violet is closed.
     *
     * @param taskList The collection of Task objects.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(formatToFile(taskList));
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
