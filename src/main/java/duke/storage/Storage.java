package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * This class saves and loads tasks into and from the text file created
 */
public class Storage {
    private static final String INVALID_DATE = "Invalid date";
    private static final String INVALID_DEADLINE = "Invalid deadline";
    private static final String INVALID_DESCRIPTION = "Invalid description";
    private static final String INVALID_EVENT = "Invalid event";
    private static final String INVALID_TASK_TYPE = "Invalid task type";
    private static final String INVALID_TODO = "Invalid todo";
    private final String filePath;

    /**
     * Constructs a new storage
     * @param filePath The relative path that describes where to store the items
     */
    public Storage(String filePath) {
        assert isPathValid(filePath) : "Invalid file path";
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Task convertToTask(String task) {
        String[] components = task.split(",");
        Task t = null;
        String completionStatus = "na";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        switch (components[0]) {
        case "T":
            assert components.length == 4 : INVALID_TODO;
            t = new ToDo(components[2]);
            completionStatus = components[3];
            break;
        case "D":
            assert components.length == 5 : INVALID_DEADLINE;
            LocalDate deadlineDate = LocalDate.parse(components[3], format);
            t = new Deadline(components[2], deadlineDate.format(formatter));
            completionStatus = components[4];
            break;
        case "E":
            assert components.length == 5 : INVALID_EVENT;
            LocalDate eventDate = LocalDate.parse(components[3], format);
            t = new Event(components[2], eventDate.format(formatter));
            completionStatus = components[4];
            break;
        default:
            assert false : INVALID_TASK_TYPE;
        }

        t.setIsDone(components[1].equals("true"));
        t.setDateMarked(completionStatus);

        return t;
    }

    /**
     * Loads data from the storage
     * @return A list of tasks
     */
    public ArrayList<Task> load() {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                Task task = convertToTask(currentLine);
                tasks.add(task);
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    private String convertToString(Task task) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String taskType = task.getTaskType();
        boolean isDone = task.getIsDone();
        String description = task.getDescription();
        String dateMarked = task.getDateMarked() == null
                ? "na"
                : task.getDateMarked().format(formatter);
        String sep = System.getProperty("line.separator");

        assert description != null : INVALID_DESCRIPTION;

        if (taskType.equals("T")) {
            return String.format("T,%s,%s,%s,%s", isDone, description, dateMarked, sep);
        } else {
            LocalDate date = task.getDate();
            assert date != null : INVALID_DATE;
            String formattedDate = date.format(formatter);
            return String.format("%s,%s,%s,%s,%s,%s", taskType, isDone, description, formattedDate, dateMarked, sep);
        }
    }

    /**
     * Saves the list of tasks to the file as described by the filePath
     * @param tasks The list of tasks
     * @throws DukeException If there are invalid inputs
     */
    public void save(TaskList tasks) throws DukeException {
        File file = new File(filePath);

        try (FileWriter fileWriter = new FileWriter(file)) {
            int len = tasks.getSize();
            for (int i = 0; i < len; i++) {
                fileWriter.write(convertToString(tasks.getTask(i)));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isPathValid(String filePath) {
        try {
            Paths.get(filePath);
            return true;
        } catch (InvalidPathException | NullPointerException e) {
            return false;
        }
    }
}
