package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Task.TaskType;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.tasks.Task.TaskType.parseTaskType;

public class Storage {

    private static Path directoryPath = Paths.get(System.getProperty("user.dir"), "data");

    private Path filePath;

    public Storage() {
        this.filePath = directoryPath.resolve("data.txt");
    }

    public Storage(String fileName) {
        this.filePath = directoryPath.resolve(fileName);
    }

    public TaskList loadFromFile() throws DukeException {
        ensureDirectoryExist();
        ensureFileExist();
        try {
            File data = new File(filePath.toString());
            Scanner sc = new Scanner(data);
            List<Task> storedTasks = new ArrayList<>();
            while (sc.hasNext()) {
                storedTasks.add(convertDataStringToTask(sc.nextLine()));
            }
            sc.close();
            return new TaskList(storedTasks);
        } catch (FileNotFoundException e) {
            throw new DukeException("Exception: Cannot open file");
        }
    }

    public void writeToFile(TaskList tasks) throws DukeException {
        List<Task> storedTasks = tasks.getStoredTasks();
        ensureDirectoryExist();
        ensureFileExist();
        try {
            FileWriter data = new FileWriter(filePath.toString());
            for (int i = 0; i < storedTasks.size(); i++) {
                data.write(convertTaskToDataString(storedTasks.get(i)));
            }
            data.close();
        } catch (IOException e) {
            throw new DukeException("Exception: Cannot open file");
        }
    }

    public void appendToFile(Task task) throws DukeException {
        ensureDirectoryExist();
        ensureFileExist();
        try {
            FileWriter data = new FileWriter(filePath.toString(), true);
            data.write(convertTaskToDataString(task));
            data.close();
        } catch (IOException e) {
            throw new DukeException("Exception: Cannot open file");
        }
    }

    private static void ensureDirectoryExist() {
        File folder = new File(directoryPath.toString());
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private void ensureFileExist() throws DukeException {
        try {
            File data = new File(directoryPath.resolve("data.txt").toString());
            if (!data.exists()) {
                data.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Exception: Unable to create new file. Tasks might not be stored.");
        }
    }

    private static String convertTaskToDataString(Task task) throws DukeException {
        switch (task.getTaskType()) {
        case TODO:
            return "T " + (task.isDone() ? "Y " : "N ") + task.getDescription() + System.lineSeparator();
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return "D " + (deadline.isDone() ? "Y " : "N ") + deadline.getDescription()
                    + "/by " + deadline.getDateTime() + System.lineSeparator();
        case EVENT:
            Event event = (Event) task;
            return "E " + (event.isDone() ? "Y " : "N ") + event.getDescription()
                    + "/at " + event.getDateTime() + System.lineSeparator();
        default:
            throw new DukeException("Exception: Unknown task type.");
        }
    }

    private static Task convertDataStringToTask(String str) throws DukeException {
        String[] taskInfo = str.split(" ", 3);
        try {
            TaskType type = parseTaskType(taskInfo[0]);
            switch (type) {
            case TODO:
                Todo todo = new Todo(taskInfo[2]);
                if ("Y".equals(taskInfo[1].strip())) {
                    todo.setIsDone(true);
                }
                return todo;
            case DEADLINE:
                String[] deadlineInfo = taskInfo[2].split("/by ", 2);
                Deadline deadline = new Deadline(deadlineInfo[0].strip(), LocalDateTime.parse(deadlineInfo[1]));
                if ("Y".equals(taskInfo[1])) {
                    deadline.setIsDone(true);
                }
                return deadline;
            case EVENT:
                String[] eventInfo = taskInfo[2].split("/at ", 2);
                Event event = new Event(eventInfo[0].strip(), LocalDateTime.parse(eventInfo[1]));
                if ("Y".equals(taskInfo[1])) {
                    event.setIsDone(true);
                }
                return event;
            default:
                throw new DukeException("Exception: Incorrect task format");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Incorrect task format.");
        }
    }
}
