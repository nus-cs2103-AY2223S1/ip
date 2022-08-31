package duke;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Storage is a class to load and save tasks.
 */
public class Storage {
    private Path relativeDirectoryPath;
    private Path relativeFilePath;
    private Path filepath;

    /**
     * Constructor for Storage.
     * @param projectRoot filepath for storage.
     */
    public Storage(String projectRoot) {
        this.relativeDirectoryPath = Paths.get(projectRoot, "data");
        this.relativeFilePath = Paths.get(projectRoot, "data", "duke.txt");
        this.filepath = Paths.get(projectRoot);
    }

    /**
     * Returns a task that is parsed from string form.
     * @param details details of the task.
     * @param taskType type of task.
     * @return task object.
     */
    public Task parseStringToTask(String details, String taskType) {
        Task task = null;
        switch (taskType) {
        case "T": {
            String[] taskDetails = details.split(" \\| ");
            task = new ToDo(taskDetails[1]);
            if (Integer.parseInt(taskDetails[0]) == 1) {
                task.markAsDone();
            }
            break;
        }
        case "D":
        case "E": {
            String[] taskDetails = details.split(" \\| ");
            task = new Deadline(taskDetails[1], taskDetails[2]);
            if (Integer.parseInt(taskDetails[0]) == 1) {
                task.markAsDone();
            }
            break;
        }
        default:
            break;
        }
        return task;
    }

    /**
     * Returns an ArrayList containing task objects that were previously saved.
     * @return ArrayList containing task objects.
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        if (!Files.exists(relativeDirectoryPath)) {
            Files.createDirectory(relativeDirectoryPath);
        }
        if (!Files.exists(relativeFilePath)) {
            Files.createFile(relativeFilePath);
        }
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(new File(String.valueOf(relativeFilePath)));
        ArrayList<String> stringTasks = new ArrayList<>();
        while(sc.hasNextLine()) {
            stringTasks.add(sc.nextLine());
        }
        for (String stringTask : stringTasks) {
            String[] taskDetails = stringTask.split(" \\| ", 2);
            Task task = null;
            String taskType = taskDetails[0];
            switch (taskType) {
            case "T": {
                task = parseStringToTask(taskDetails[1], "T");
                break;
            }
            case "D": {
                task = parseStringToTask(taskDetails[1], "D");
                break;
            }
            case "E": {
                task = parseStringToTask(taskDetails[1], "E");
                break;
            }
            default:
                break;
            }
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to hard disk.
     * @param tasks ArrayList containing tasks.
     * @throws IOException
     */
    public void save(ArrayList<Task> tasks) {
        try {
            ArrayList<String> stringTasks = new ArrayList<>();
            for (Task task : tasks) {
                switch (task.getTaskType()) {
                    case "T": {
                        String data = "T" + " | " + task.getStatus() + " | " + task.getDescription();
                        stringTasks.add(data);
                        break;
                    }
                    case "D": {
                        String data = "D" + " | " + task.getStatus() + " | " + task.getDescription();
                        stringTasks.add(data);
                        break;
                    }
                    case "E": {
                        String data = "E" + " | " + task.getStatus() + " | " + task.getDescription();
                        stringTasks.add(data);
                        break;
                    }
                    default:
                        break;
                }
            }
            FileWriter fileWriter = new FileWriter(String.valueOf(relativeFilePath));
            for (String stringTask : stringTasks) {
                fileWriter.write(stringTask + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
