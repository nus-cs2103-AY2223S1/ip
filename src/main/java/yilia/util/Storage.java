package yilia.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import yilia.task.Deadline;
import yilia.task.Event;
import yilia.task.Task;
import yilia.task.TaskList;
import yilia.task.Todo;

/**
 * Represents the storage to load and save data.
 */
public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads tasks from a local file to the task list.
     *
     * @return The task list loaded.
     * @throws IOException  If the file has an error.
     */
    public ArrayList<Task> load() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        ArrayList<Task> tasks = new ArrayList<>();
        while (line != null) {
            String[] info = line.split(" / ");
            switch (info[0]) {
            case "T":
                tasks.add(new Todo(info[2], Parser.parseStringToBoolean(info[1])));
                break;
            case "D":
                tasks.add(new Deadline(info[2], Parser.parseStringToBoolean(info[1]), info[3]));
                break;
            default:
                tasks.add(new Event(info[2], Parser.parseStringToBoolean(info[1]), info[3]));
                break;
            }
            line = reader.readLine();
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves tasks into a local file.
     *
     * @param tasks The task list.
     * @throws IOException  If the file has an error.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 1; i <= tasks.size(); i++) {
            writer.write(tasks.get(i).parse());
            writer.write('\n');
        }
        writer.close();
    }
}
