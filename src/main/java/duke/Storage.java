package duke;
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

    /**
     * Prints the contents of the file onto the console.
     *
     * @param filePath Relative path of the file to be loaded from.
     * @throws FileNotFoundException If filePath is invalid.
     */
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Reformats the content from the saved file into the list.
     *
     * @param f File to be loaded from.
     * @param taskList The collection of Task objects.
     * @throws FileNotFoundException If file does not exist.
     */
    public static void formatToList(File f, ArrayList<Task> taskList) throws FileNotFoundException {
        Scanner s = new Scanner(f);

        try {
            while (s.hasNextLine()) {
                String[] taskDescription = s.nextLine().split(" \\| ");
                String taskType = taskDescription[0];
                boolean isDone = taskDescription[1].equals("1");
                String description = taskDescription[2] + " ";
                String date = " ";
                Task task = null;

                if (taskDescription.length == 4) {
                    date += taskDescription[3];
                }

                if (taskType.equals("T")) {
                    task = new ToDo(description, isDone);
                }

                if (taskType.equals("D")) {
                    task = new Deadline(description, isDone, date);
                }

                if (taskType.equals("E")) {
                    task = new Event(description, isDone, date);
                }

                if (isDone) {
                    task.markAsDone();
                }

                taskList.add(task);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the file when Duke is opened.
     *
     * @return The collection of Tasks that was saved in the file.
     * @throws DukeException If file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
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
            throw new DukeException("Error: Cannot load file!");
        } catch (IOException e) {
            throw new DukeException("Error: File does not exist");
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
     * Saves the list into the file when Duke is closed.
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
