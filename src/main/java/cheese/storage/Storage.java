package cheese.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.task.Deadline;
import cheese.task.Event;
import cheese.task.Task;
import cheese.task.Todo;

/**
 * Represents a storage that interacts with <code>Cheese</code>'s save file.
 */
public class Storage {
    /** File path of save file. */
    private String filePath;

    /**
     * Constructs an instance of <code>Storage</code>.
     * 
     * @param filePath File path of save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from save file.
     * 
     * @return Instance of <code>TaskList</code> loaded from save file.
     * @throws CheeseException If save file contains corrupted data.
     */
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

    /**
     * Saves given task list to save file.
     * 
     * @param taskList Task list to save.
     */
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

    /**
     * Parses string from save file to task.
     * 
     * @param taskString String from save file to parse.
     * @return Task parsed from string.
     * @throws CheeseException If string from save file is corrupted.
     */
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
