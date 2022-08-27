package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage, stores and read data from given file location
 */
public class Storage {
    protected String dataFileName;

    /**
     * Instantiates a new Storage object
     */
    public Storage(String filePath) {
        this.dataFileName = filePath;
    }

    /**
     * Writes the contents of a given TaskList to a specified file
     *
     * @param taskL the TaskList containing Task to be written to file
     */
    public void writeToFile(TaskList taskL) throws IOException {
        //structure: command|1 (1 for mark, 0 for unmark)
        ArrayList<Task> arr = taskL.getTasks();
        FileWriter fw = new FileWriter(this.dataFileName);
        for (Task task : arr) {
            fw.write(task.getData() + "\n");
        }
        fw.close();
    }

    /**
     * Loads the contents of a given TaskList from a specified file
     *
     * @param taskL the TaskList to contain Tasks loaded from a specified file
     */
    public void loadFromFile(TaskList taskL) {
        try {

            File f = new File(dataFileName);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                taskL.add(new Task(line));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
