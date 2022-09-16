package yilia.util;


import java.io.BufferedReader;
import java.io.File;
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
    protected File file;
    public Storage(String filePath) {
        this.file = new File(filePath);
    }
    /**
     * Loads tasks from a local file to the task list.
     *
     * @return The task list loaded.
     * @throws IOException  If the file has an error.
     */
    public ArrayList<Task> load() throws IOException {
        initializeFile();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
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
            line = bufferedReader.readLine();
        }
        reader.close();
        return tasks;
    }

    /**
     * Creates the directory and the file if not already exist.
     */
    public void initializeFile() {
        File directory = new File(this.file.getParent());
        if (!directory.exists()) {
            directory.mkdir();
        }
        assert directory.exists() : "The directory should already exist";
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            assert file.exists() : "The file should already exist";
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Saves tasks into a local file.
     *
     * @param tasks The task list.
     * @throws IOException  If the file has an error.
     */
    public void save(TaskList tasks) throws IOException {
        initializeFile();
        FileWriter writer = new FileWriter(file);
        for (int i = 1; i <= tasks.size(); i++) {
            writer.write(tasks.get(i).parse());
            writer.write('\n');
        }
        writer.close();
    }
}
