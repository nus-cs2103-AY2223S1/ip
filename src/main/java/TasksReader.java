import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TasksReader {
    private String userDirectory = System.getProperty("user.dir");

    TasksReader() {
    }

    public ArrayList<Task> readSavedTasks() throws DukeException {
        Path dataDirectoryPath = Paths.get(userDirectory, "data");
        Path savedTasksPath = Paths.get(userDirectory, "data", "duke.txt");
        if (!hasDataDirectory(dataDirectoryPath)) {
            createDataDirectory(dataDirectoryPath);
            return new ArrayList<>();
        }
        if (!hasSavedTasks(savedTasksPath)) {
            return new ArrayList<>();
        } else {
            File tasksFile = getTasksFile(savedTasksPath);
            ArrayList<Task> tasks = new ArrayList<>();
            try {
                tasks = parseFileToTasks(tasksFile);
            } catch (FileNotFoundException e) {
                throw new DukeException("Eh something went wrong " + e);
            } finally {
                return tasks;
            }
        }
    }

    private File getTasksFile(Path savedTasksPath) {
        File tasksFile = new File(getAbsolutePath(savedTasksPath));
        return tasksFile;
    }

    private ArrayList<Task> parseFileToTasks(File tasksFile) throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(tasksFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String taskDescription = sc.nextLine();
            tasks.add(parseLineToTask(taskDescription));
        }
        return tasks;
    }

    private Task parseLineToTask(String line) throws DukeException {
        if (line.length() <= 7) {
            throw new DukeException("Eh something wrong happened");
        }
        char taskSymbol = line.charAt(1);
        boolean isDone = line.charAt(4) == 'x'
                ? true
                : false;
        switch (taskSymbol) {
        case 'T':
            return getTodoFromLine(line);
        case 'D':
            return getTaskFromLine(line, isDone, " \\(by: ");
        case 'E':
            return getTaskFromLine(line, isDone, " \\(at: ");
        default:
            throw new DukeException("Eh something wrong happened");
        }
    }

    private Event getTaskFromLine(String line, boolean isDone, String regex) {
        String[] splittedEvent = line.substring(7).split(" \\(at: ", 3);
        return new Event(splittedEvent[0], splittedEvent[1].substring(0, splittedEvent[1].length() - 1), isDone);
    }

    private Todo getTodoFromLine(String line) {
        return new Todo(line.substring(7), false);
    }

    private String getAbsolutePath(Path path) {
        return path.toAbsolutePath().toString();
    }

    private void createDataDirectory(Path dataDirectoryPath) throws DukeException {
        try {
            Files.createDirectories(dataDirectoryPath);
        } catch (IOException e) {
            System.out.println("Eh something went wrong ah!");
        }
    }

    private boolean hasDataDirectory(Path dataDirectoryPath) {
        return Files.exists(dataDirectoryPath);
    }

    private boolean hasSavedTasks(Path savedTasksPath) {
        return Files.exists(savedTasksPath);
    }
}
