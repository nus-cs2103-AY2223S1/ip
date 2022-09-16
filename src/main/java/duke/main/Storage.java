package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tag.Tag;
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
            StringBuilder taskString = new StringBuilder();
            for (Task task : tasks.getTasks()) {
                taskString.append(taskString + task.toSaveString() + "\n");
            }
            fw.write(taskString.toString());
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
                    String[] line = sc.nextLine().split(" \\| ", 5);
                    if (line.length < 3) {
                        throw new DukeException(EVIL_FILE);
                    }

                    boolean isDone = loadIsDone(line[1]);
                    String description = line[2];
                    Task t = loadTask(line, description, isDone);
                    tasks.add(t);

                }
            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException(OH_MAN + e.getMessage());
        }
    }

    /**
     * Loads whether task has been done or not
     *
     * @param isDone String representation of whether task has been done
     * @return Boolean value representing isDone status of task
     * @throws DukeException If file is corrupted
     */
    public boolean loadIsDone(String isDone) throws DukeException {
        if (isDone.equals("1")) {
            return true;
        } else if (isDone.equals("0")) {
            return false;
        } else {
            throw new DukeException(EVIL_FILE);
        }
    }

    /**
     * Loads task from storage
     *
     * @param line        Input by user
     * @param description Task description
     * @param isDone      Boolean value representing if task is completed
     * @return Task to be added
     * @throws DukeException @inheritDoc
     */
    public Task loadTask(String[] line, String description, boolean isDone) throws DukeException {
        switch (line[0]) {
        case "T":
            Task t0 = new ToDoTask(description, isDone);
            loadTag(line, t0);
            return t0;

        case "D":
            if (line.length < 5) {
                throw new DukeException(EVIL_FILE);
            }
            try {
                LocalDate by = LocalDate.parse(line[4]);
                Task t1 = new DeadlineTask(description, by, isDone);
                loadTag(line, t1);
                return t1;
            } catch (DateTimeParseException e) {
                throw new DukeException(EVIL_FILE);
            }

        case "E":
            if (line.length < 5) {
                throw new DukeException(EVIL_FILE);
            }
            try {
                LocalDate at = LocalDate.parse(line[4]);
                Task t2 = new EventTask(description, at, isDone);
                loadTag(line, t2);
                return t2;
            } catch (DateTimeParseException e) {
                throw new DukeException(EVIL_FILE);
            }
        default:
            throw new DukeException(EVIL_FILE);
        }
    }

    /**
     * Loads tag of Task
     *
     * @param line Input by user
     * @param t    Task to add tag
     * @throws DukeException @inheritdoc
     */
    public void loadTag(String[] line, Task t) throws DukeException {
        if (!line[3].equals("")) {
            t.addTag(new Tag(line[3]));
        }
    }
}
