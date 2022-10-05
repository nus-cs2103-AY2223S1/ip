package ted;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import ted.exception.InvalidEncodingException;
import ted.task.Task;
import ted.task.TaskList;

/**
 * This class acts as a helper to load tasks from file
 * and save tasks to file.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from storage, return empty array if file not found
     * or cannot be opened.
     *
     * @return TaskList
     * @throws InvalidEncodingException
     */
    public TaskList loadTasks() throws InvalidEncodingException {
        File file = new File(filePath);
        if (!file.exists()) {
            return TaskList.empty();
        }

        try {
            Scanner s = new Scanner(file);
            TaskList tasks = TaskList.empty();
            while (s.hasNext()) {
                String encoded = s.nextLine();
                tasks.add(Task.decode(encoded));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return TaskList.empty();
        }
    }

    private String encodeTasks(TaskList tasks) {
        String result = "";
        for (int index = 0; index < tasks.size(); index++) {
            result += tasks.get(index).encode() + "\n";
        }
        return result;
    }

    /**
     * Saves tasks into starage.
     *
     * @param tasks Tasks list to save
     * @throws IOException
     * @throws SecurityException
     */
    public void saveTasks(TaskList tasks) throws IOException, SecurityException {
        File file = new File(filePath);
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(encodeTasks(tasks));
        fw.close();
    }
}
