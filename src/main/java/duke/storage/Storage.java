package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasklist.Task;
import duke.tasklist.TaskList;

/**
 * Storage for the overall Duke application.
 * Handles reading and writing to files.
 */
public class Storage {

    /** Storage object to preserve singleton design */
    private static Storage storage;

    /**
     * Gets storage object.
     *
     * @return Storage object.
     */
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }


    /**
     * Reads tasks saved in data file, handles cases of missing file.
     */
    public void readSavedTasks() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("yyyy MM dd HH:mm");
            File dir = new File("data/");
            if (!dir.exists()) {
                boolean makeDir = dir.mkdir();
            }
            File temp = new File("data/duke.txt");
            if (!temp.exists()) {
                boolean makeFile = temp.createNewFile();
            }

            Scanner in = new Scanner(temp);
            while (in.hasNext()) {
                String curr = in.nextLine();
                TaskList list = TaskList.getInstance();
                list.addTasksFromSave(curr);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes tasks to data file, to same location as input file is expected.
     */
    public void writeToSavedFile() {
        try {
            FileWriter writer = new FileWriter("data/duke.txt");
            ArrayList<Task> storage = TaskList.getInstance().getTaskList();
            for (Task x : storage) {
                writer.write(x.getSavedFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
