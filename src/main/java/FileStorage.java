import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private Path directoryPath;
    private Path filePath;
    public FileStorage(String home) {
        this.directoryPath = Paths.get(home, "dukeData");
        this.filePath = Paths.get(home, "dukeData", "Duke.txt");
    }

    public boolean isDirectoryPresent() {
        return Files.exists(directoryPath);
    }

    public boolean isFilePresent() {
        return Files.exists(filePath);
    }

    public void createDirectory() {
        try {
            Files.createDirectory(directoryPath);
        } catch (IOException e) {
            System.out.println("Directory cannot be created at this path");
        }
    }

    public void createFile() {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println("File cannot be created at this path");
        }
    }

    public ArrayList<Task> retrieveFileContents() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            List<String> contents = Files.readAllLines(filePath);
            for (String content : contents) {
                String[] components = content.split(" \\| ", 2);
                Task task = null;
                switch (components[0]) {
                case "T": {
                    task = createTask(components[1], TaskType.TODO);
                    break;
                }
                case "D": {
                    task = createTask(components[1], TaskType.DEADLINE);
                    break;
                }
                case "E": {
                    task = createTask(components[1], TaskType.EVENT);
                    break;
                }
                default:
                    break;
                }
                if (task != null) {
                    listOfTasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred when reading from file");
        }
        return listOfTasks;
    }

    private Task createTask(String input, TaskType type) {
        Task task = null;
        int taskStatus = 0;
        String[] components;
        switch (type) {
        case TODO: {
            components = input.split(" \\| ", 2);
            task = new ToDo(components[1], type);
            taskStatus = Integer.parseInt(components[0]);
            break;
        }
        case DEADLINE:
        case EVENT: {
            components = input.split(" \\| ", 3);
            task = createTaskWithDate(components[1], components[2], type);
            taskStatus = Integer.parseInt(components[0]);
            break;
        }
        default:
            break;
        }
        if (task != null & taskStatus == 1) {
            task.markAsDone();
        }
        return task;
    }
    public void writeToFile(List<Task> taskList) {
        try {
            String string;
            List<String> list = new ArrayList<>();
            for (Task task : taskList) {
                switch (task.type) {
                case TODO: {
                    string = "T | " + task.getDoneStatus() + " | " + task.getDescription();
                    break;
                }
                case DEADLINE: {
                    string = "D | " + task.getDoneStatus() + " | " + task.getDescription();
                    break;
                }
                case EVENT: {
                    string = "E | " + task.getDoneStatus() + " | " + task.getDescription();
                    break;
                }
                default:
                    string = "";
                    break;
                }
                if (!string.equals("")) {
                    list.add(string);
                }
            }
            Files.write(filePath, list);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private Task createTaskWithDate(String description, String timePeriod, TaskType type) {
        LocalTime time = null;
        LocalDate date;
        Task task = null;
        String[] components = timePeriod.split(" ", 2);
        date = LocalDate.parse(components[0]);
        switch (type) {
        case DEADLINE:
            if (components.length != 1) {
                time = LocalTime.parse(components[1]);
            }
            task = new Deadline(description, date, time, type);
            break;
        case EVENT:
            LocalTime timeEnd;
            if (components.length == 1) {
                throw new DateTimeParseException("", components[0], 1);
            } else {
                String[] duration = components[1].split("-", 2);
                time = LocalTime.parse(duration[0]);
                timeEnd = LocalTime.parse(duration[1]);
            }
            task = new Event(description, date, time, timeEnd, type);
            break;
        }
        return task;
    }
}
