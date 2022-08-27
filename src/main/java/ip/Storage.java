package ip;

import ip.task.Deadline;
import ip.task.Event;
import ip.task.Task;
import ip.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private final String path;
    private final File file;

    public Storage(String path) {
        this.path = path;
        file = new File(path);
    }

    public TaskList load() throws IOException {
        System.out.println("Searching for existing task data...");
        if (file.createNewFile()) {
            System.out.println("No existing task data. New database initialized.");
            return new TaskList();
        } else {
            System.out.println("Existing task data found. Loading...");
            TaskList taskList = new TaskList();
            String data = new String(Files.readAllBytes(Path.of(path)));
            String[] lines = data.split("\\r?\\n");
            for (String line : lines) {
                String[] info = line.split("\\|");
                String taskType = info[0];
                switch (taskType) {
                case "t":
                    taskList.add(new ToDo(info));
                    break;
                case "d":
                    taskList.add(new Deadline(info));
                    break;
                case "e":
                    taskList.add(new Event(info));
                    break;
                default:
                    System.out.println("Invalid task format found in existing data. Line skipped.");
                }
            }
            System.out.println("Existing data loaded.");
            return taskList;
        }
    }

    public void write(TaskList taskList) {
        try {
            FileWriter target = new FileWriter(path);
            for (Task task : taskList.tasks) {
                target.append(task.writeFormat());
            }
            target.close();
            System.out.println("Current task list successfully saved.");
        } catch (IOException e) {
            System.out.println("Error in writing file.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "File containing task data saved at \"" + path + "\".";
    }
}
