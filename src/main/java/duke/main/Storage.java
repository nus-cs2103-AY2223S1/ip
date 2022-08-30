package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDoTask;


/**
 * Storage class handles saving and loading from data file to TaskList
 */
public class Storage {

    private static final String OH_MAN = "Oh man, ";
    private static final String EVIL_FILE = "Duke thinks this file is evil and corrupted.";
    private final String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath Filepath of data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data from TaskList to data File
     *
     * @param tasks TaskList
     * @throws DukeException If there is error when saving to data file
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            String taskString = "";
            for (Task task : tasks.getTasks()) {
                taskString += task.toSaveString() + "\n";
            }
            fw.write(taskString);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(OH_MAN + e.getMessage());
        }
    }

    /**
     * Loads data file from storage to TaskList
     *
     * @throws DukeException If there are issues with current data file such as invalid location or corrupted file
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            File data = new File(this.filePath);

            File parent = data.getParentFile();

            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new DukeException("Could not create directory: " + parent);
            }

            data.createNewFile();
            data.mkdir();

            try (Scanner sc = new Scanner(data)) {
                while (sc.hasNext()) {
                    String[] line = sc.nextLine().split(" \\| ", 4);
                    if (line.length < 3) {
                        throw new DukeException(EVIL_FILE);
                    }

                    String description = line[2];
                    boolean isDone;

                    if (line[1].equals("1")) {
                        isDone = true;
                    } else if (line[1].equals("0")) {
                        isDone = false;
                    } else {
                        throw new DukeException(EVIL_FILE + '\n' + line.length);
                    }

                    switch (line[0]) {
                    case "T":
                        tasks.add(new ToDoTask(description, isDone));
                        break;
                    case "D":
                        if (line.length < 4) {
                            throw new DukeException(EVIL_FILE);
                        }
                        try {
                            LocalDate by = LocalDate.parse(line[3]);

                            tasks.add(new DeadlineTask(description, by, isDone));
                        } catch (DateTimeParseException e) {
                            throw new DukeException(EVIL_FILE);
                        }
                        break;
                    case "E":
                        if (line.length < 4) {
                            throw new DukeException(EVIL_FILE);
                        }
                        try {
                            LocalDate at = LocalDate.parse(line[3]);
                            tasks.add(new EventTask(description, at, isDone));
                        } catch (DateTimeParseException e) {
                            throw new DukeException(EVIL_FILE);
                        }
                        break;
                    default:
                        throw new DukeException(EVIL_FILE);
                    }
                }
                sc.close();
            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException(OH_MAN + e.getMessage());
        }
    }
}
