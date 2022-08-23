package blink;

import blink.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

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
     * be found.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Loads the save file and its information into Blink program.
     *
     * @return ArrayList of Task found on save file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
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
        } catch (FileNotFoundException e) {
            throw new BlinkException("Save file not found");
        }
        return tasks;
    }

    /**
     * Creates a save file to replace the existing one.
     *
     * @param tasks TaskList containing all tasks inputted into
     * the Blink program
     */
    public void save(TaskList tasks) {
        try {
            new File(this.filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(this.filePath);
            for (int x = 0; x < tasks.length(); x++) {
                Task temp = tasks.get(x);
                fw.write(temp.saveString());
            }
            fw.close();
        } catch (IOException e) {
            throw new BlinkException("Unable to save");
        }
    }

}
