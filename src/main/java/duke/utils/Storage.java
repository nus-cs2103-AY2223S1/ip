package duke.utils;

import duke.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores and loads the tasks inputted into Duke to local storage.
 */
public class Storage {
    private String path;
    private File file;

    public Storage(String path) {
        this.path = path;
        this.file = new File(this.path);
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(this.path);
        for (Task task : tasks) {
            fileWriter.write(task.toSaveString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task parsedTask = Parser.parseSave(scanner.nextLine());
                tasks.add(parsedTask);
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }

        return tasks;
    }
}
