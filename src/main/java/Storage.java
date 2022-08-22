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

public class Storage {

    private static Path directoryPath = Paths.get(System.getProperty("user.dir"), "data");
    private static Path filePath = directoryPath.resolve("data.txt");

    public static List<Task> readFromFile() throws DukeException {
        List<Task> storedTasks = new ArrayList<>();
        checkDirectory();
        checkFile();
        try {
            File data = new File(filePath.toString());
            Scanner sc = new Scanner(data);
            while (sc.hasNext()) {
                storedTasks.add(readTaskString(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Exception: Cannot open file");
        }
        return storedTasks;
    }

    public static void writeToFile(List<Task> storedTasks) throws DukeException {
        checkDirectory();
        checkFile();
        try {
            FileWriter data = new FileWriter(filePath.toString());
            for (int i = 0; i < storedTasks.size(); i++) {
                data.write(taskToStorageString(storedTasks.get(i)));
            }
            data.close();
        } catch (IOException e) {
            throw new DukeException("Exception: Cannot open file");
        }
    }

    public static void appendToFile(Task task) throws DukeException {
        checkDirectory();
        checkFile();
        try {
            FileWriter data = new FileWriter(filePath.toString(), true);
            data.write(taskToStorageString(task));
            data.close();
        } catch (IOException e) {
            throw new DukeException("Exception: Cannot open file");
        }
    }

    private static void checkDirectory() {
        File folder = new File(directoryPath.toString());
        if (!folder.exists()) {
            System.out.println("N");
            folder.mkdir();
        }
    }

    private static void checkFile() {
        try {
            File data = new File(directoryPath.resolve("data.txt").toString());
            if (!data.exists()) {
                System.out.println("new file");
                data.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String taskToStorageString(Task task) throws DukeException {
        switch (task.getType()) {
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

    private static Task readTaskString(String str) throws DukeException {
        String[] taskInfo = str.split(" ", 3);
        try {
            Task.TaskType type = Task.TaskType.parseTaskType(taskInfo[0]);
            switch (type) {
            case TODO:
                Todo todo = new Todo(taskInfo[2]);
                if ("Y".equals(taskInfo[1].strip())) {
                    todo.markAsDone();
                }
                return todo;
            case DEADLINE:
                String[] deadlineInfo = taskInfo[2].split("/by ", 2);
                Deadline deadline = new Deadline(deadlineInfo[0].strip(), LocalDateTime.parse(deadlineInfo[1]));
                if ("Y".equals(taskInfo[1])) {
                    deadline.markAsDone();
                }
                return deadline;
            case EVENT:
                String[] eventInfo = taskInfo[2].split("/at ", 2);
                Event event = new Event(eventInfo[0].strip(), LocalDateTime.parse(eventInfo[1]));
                if ("Y".equals(taskInfo[1])) {
                    event.markAsDone();
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
