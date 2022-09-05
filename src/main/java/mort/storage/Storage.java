package mort.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import mort.exception.MortException;
import mort.parser.Parser;
import mort.task.Deadline;
import mort.task.Event;
import mort.task.Task;
import mort.task.TaskList;
import mort.task.ToDo;

/**
 * Class that contains methods to save tasks to a file
 * and load tasks from a file.
 */
public class Storage {
    /** Path to directory of saved file */
    private Path directory;
    /** Directory where file is saved */
    private File fileDirectory;
    /** Path to the saved file */
    private Path filePath;
    /** List containing tasks loaded from file */
    private ArrayList<Task> tasks;

    /**
     * Constructor to initialize directory and path of the saved file and
     * the list of tasks.
     */
    public Storage() {
        this.directory = Paths.get("data");
        this.fileDirectory = new File(this.directory.toString());
        this.filePath = Paths.get(this.directory.toString(), "mort.txt");
        this.tasks = new ArrayList<>();
    }

    /**
     * Saves the list of tasks to a text file.
     * @param tasks List of tasks to be saved.
     * @throws MortException If tasks list cannot be saved.
     */
    public void save(TaskList tasks) throws MortException {
        try {
            String savedTasks = tasks.getSaveFormat();
            if (Files.exists(this.directory)) {
                Files.write(this.filePath, savedTasks.getBytes());
            } else {
                this.fileDirectory.mkdirs();
                Files.write(this.filePath, savedTasks.getBytes());
            }
        } catch (IOException e) {
            throw new MortException("  Error: Unable to save tasks.");
        }
    }

    /**
     * Loads the list of tasks from a given text file.
     * @return List of tasks from the given text file.
     * @throws MortException If task list cannot be loaded.
     */
    public ArrayList<Task> load() throws MortException {
        try {
            if (Files.exists(this.directory) && Files.exists(this.filePath)) {
                List<String> allLines = Files.readAllLines(this.filePath);
                for (String line : allLines) {
                    String[] parsedTask = Parser.parseSavedTask(line);
                    boolean isDone = parsedTask[1].contains("1");
                    String desc = parsedTask[2];
                    Task task = null;
                    switch (parsedTask[0]) {
                    case "T":
                        task = new ToDo(desc, isDone);
                        break;
                    case "D":
                        if (parsedTask[3].contains(" ")) {
                            LocalDateTime dateTime = Parser.convertStringToDateTime(parsedTask[3]);
                            task = new Deadline(desc, dateTime, isDone);
                        } else {
                            LocalDate date = Parser.convertStringToDate(parsedTask[3]);
                            task = new Deadline(desc, date, isDone);
                        }
                        break;
                    case "E":
                        if (parsedTask[3].contains(" ")) {
                            LocalDateTime dateTime = Parser.convertStringToDateTime(parsedTask[3]);
                            task = new Event(desc, dateTime, isDone);
                        } else {
                            LocalDate date = Parser.convertStringToDate(parsedTask[3]);
                            task = new Event(desc, date, isDone);
                        }
                        break;
                    }
                    this.tasks.add(task);
                }
            }
            return this.tasks;
        } catch (IOException e) {
            throw new MortException();
        }
    }
}
