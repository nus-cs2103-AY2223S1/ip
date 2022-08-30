package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A class which handles the saving and loading of data.
 */
public class Storage {
    public static final String FILE_PATH = "data/duke.txt";

    private String path;

    /**
     * Constructs a new Storage object and creates an empty save file if one does not exist.
     * @param path Path of the save file.
     * @throws DukeException If the save file cannot be created.
     */
    public Storage(String path) throws DukeException {
        this.path = path;
        File saveFile = new File(this.path);
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Unable to create save file");
            }
        }
    }

    /**
     * Loads from the save file at path specified in the constructor.
     * @return An ArrayList of Task objects that the save file saved.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> output = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(Path.of(this.path));
        } catch (IOException e) {
            return new ArrayList<>();
        }
        try {
            while (sc.hasNextLine()) {
                String[] saveLine = sc.nextLine().split(Task.SAVE_SEPARATOR);
                switch (saveLine[0]) {
                case "T":
                    output.add(new ToDo(saveLine[2], saveLine[1].equals("1")));
                    break;
                case "E":
                    output.add(new Event(saveLine[2], LocalDate.parse(saveLine[3]), saveLine[1].equals("1")));
                    break;
                case "D":
                    output.add(new Deadline(saveLine[2], LocalDate.parse(saveLine[3]), saveLine[1].equals("1")));
                    break;
                default:
                    break;
                }
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return output;
    }

    /**
     * Saves a TaskList object to the save file.
     * @param taskList The TaskList object to be saved.
     * @throws DukeException If the save file cannot be accessed.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            for (Task t : taskList.getTaskList()) {
                fileWriter.write(t.toSave());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Cannot save to file");
        }
    }
}
