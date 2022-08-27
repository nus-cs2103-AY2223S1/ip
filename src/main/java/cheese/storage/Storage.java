package cheese.storage;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import cheese.data.TaskList;
import cheese.task.Deadline;
import cheese.task.Event;
import cheese.task.Task;
import cheese.task.Todo;
import cheese.exception.CheeseException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws CheeseException {
        TaskList taskList = new TaskList();
        try {
            File file = new File(filePath);
            file.createNewFile();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                Task task = decodeStringToTask(scanner.nextLine());
                taskList.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public void save(TaskList taskList) {
        String toSave = taskList.toFileString();
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(toSave);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Task decodeStringToTask(String taskString) throws CheeseException {
        String[] taskStringArray = taskString.split(" // ");
        String taskType = taskStringArray[0];
        boolean isDone = taskStringArray[1].equals("T");
        String description = taskStringArray[2];
        switch (taskType) {
        case "todo":
            return new Todo(isDone, description);
        case "deadline":
            String deadline = taskStringArray[3];
            return new Deadline(isDone, description, deadline);
        case "event":
            String timeInterval = taskStringArray[3];
            return new Event(isDone, description, timeInterval);
        default:
            throw new CheeseException("Save file is corrupted");
        }
    }
}
