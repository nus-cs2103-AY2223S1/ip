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
    private String filePath;

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

    /**
     * Loads data from the storage
     * @return A list of tasks
     */
    public ArrayList<Task> load() {
        File file = new File(filePath);
        BufferedReader reader = null;
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try {
            reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                String[] components = currentLine.split(",");
                Task task = null;
                String completionStatus = "na";
                switch (components[0]) {
                case "T":
                    assert components.length == 4 : "Invalid todo information";
                    task = new ToDo(components[2]);
                    completionStatus = components[3];
                    break;
                case "D":
                    assert components.length == 5 : "Invalid deadline information";
                    LocalDate deadlineDate = LocalDate.parse(components[3], format);
                    task = new Deadline(components[2], deadlineDate.format(formatter));
                    completionStatus = components[4];
                    break;
                case "E":
                    assert components.length == 5 : "Invalid event information";
                    LocalDate eventDate = LocalDate.parse(components[3], format);
                    task = new Event(components[2], eventDate.format(formatter));
                    completionStatus = components[4];
                    break;
                default:
                    assert true : "Invalid task type";
                    break;
                }

                assert task != null : "Task should not be null";
                task.setIsDone(components[1].equals("true"));
                task.setDateMarked(completionStatus);
                tasks.add(task);
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
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

        assert description != null : "Invalid description";

        if (taskType.equals("T")) {
            return String.format("T,%s,%s,%s,%s", isDone, description, dateMarked, sep);
        } else {
            LocalDate date = task.getDate();
            assert date != null : "Invalid date";
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
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            int len = tasks.getSize();
            for (int i = 0; i < len; i++) {
                fileWriter.write(convertToString(tasks.getTask(i)));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static boolean isPathValid(String filePath) {
        try {
            Paths.get(filePath);
            return true;
        } catch (InvalidPathException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
