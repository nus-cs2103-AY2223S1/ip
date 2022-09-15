package blink;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import blink.task.Task;

/**
 * Loads the save file when the program starts and save a new
 * file at certain commands.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor of Storage
     *
     * @param filePath The filepath where the save file can
     *     be found.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Loads the save file and its information into Blink program.
     *
     * @return ArrayList of Task found on save file
     * @throws FileNotFoundException Throws when no save file to load
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File saveFile = new File(this.filePath);
        Scanner sc = new Scanner(saveFile);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            Task temp = Task.readSaveTask(line);
            tasks.add(temp);
        }
        return tasks;
    }

    /**
     * Creates a save file to replace the existing one.
     *
     * @param tasks TaskList containing all tasks inputted into
     *     the Blink program
     */
    public void save(TaskList tasks) {
        try {
            new File(this.filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(this.filePath);
            for (int x = 0; x < tasks.length(); x++) {
                Task temp = tasks.getTask(x);
                fw.write(temp.saveString());
            }
            fw.close();
        } catch (IOException e) {
            throw new BlinkException("Unable to save");
        }
    }

}
