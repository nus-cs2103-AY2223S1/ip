package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        File file = new File(this.filePath);
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
                switch (components[0]) {
                case "T":
                    task = new ToDo(components[2]);
                    break;
                case "D":
                    LocalDate deadlineDate = LocalDate.parse(components[3], format);
                    task = new Deadline(components[2], deadlineDate.format(formatter));
                    break;
                case "E":
                    LocalDate eventDate = LocalDate.parse(components[3], format);
                    task = new Event(components[2], eventDate.format(formatter));
                    break;
                default:
                    break;
                }

                task.setIsDone(components[1].equals("true"));
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
        String taskType = task.getTaskType();
        boolean isDone = task.getIsDone();
        String description = task.getDescription();
        String sep = System.getProperty("line.separator");

        if (taskType.equals("T")) {
            return String.format("T,%s,%s,%s", isDone, description, sep);
        } else {
            LocalDate date = task.getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            String formattedDate = date.format(formatter);
            return String.format("%s,%s,%s,%s,%s", taskType, isDone, description, formattedDate, sep);
        }
    }

    /**
     * Saves the list of tasks to the file as described by the filePath
     * @param tasks The list of tasks
     * @throws DukeException If there are invalid inputs
     */
    public void save(TaskList tasks) throws DukeException {
        File file = new File(this.filePath);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            int len = tasks.getSize();
            for (int i = 0; i < len; i++) {
                fileWriter.write(this.convertToString(tasks.getTask(i)));
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
}
