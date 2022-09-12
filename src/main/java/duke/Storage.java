package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * The class that deals with loading tasks from the file and
 * saving tasks in the file in the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Storage {

    private final String FILE_PATH;

    /**
     * Initializes a Storage object with the specified file path to load and store tasks.
     *
     * @param filePath The file path to the local file responsible for loading and saving.
     */

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Loads the local data stored in the Duke program and returns an ArrayList
     * containing all the Tasks stored in the local data.
     *
     * @return the ArrayList containing all the Tasks stored in the local data.
     * @throws DukeException if the local data does not exist.
     */

    public ArrayList<Task> loadLocalData() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(this.FILE_PATH);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("##");
                Task task;
                switch (split[0]) {
                case "D": {
                    LocalDate ld = LocalDate.parse(split[3]);
                    task = new Deadline(split[2], ld);
                    break;
                }
                case "E": {
                    LocalDate ld = LocalDate.parse(split[3]);
                    task = new Event(split[2], ld);
                    break;
                }
                case "T":
                    task = new Todo(split[2]);
                    break;
                default:
                    throw new DukeException("Unable to read file.");
                }
                if ("Y".equals(split[1])) {
                    task.toggleDoneness();
                }
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("data/"));
                File file = new File("data/duke.txt");
                file.createNewFile();
                System.out.print("New file created to store tasks.");
            } catch (IOException ex) {
                throw new DukeException(ex.getMessage());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to read file.");
        } finally {
            return taskList;
        }
    }

    /**
     * Saves the TaskList into the local data everytime the TaskList changes.
     *
     * @throws DukeException if IOException is thrown
     */

    public void saveLocalData(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : taskList) {
                String str = task.stringify();
                fw.write(str + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
