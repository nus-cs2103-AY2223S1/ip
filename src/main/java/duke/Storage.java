package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import duke.controller.Ui;
import duke.task.Task;

/**
 * Represents a storage class for I/O operations.
 */
public class Storage {
    private final String filePath;

    /**
     * Returns a storage instance.
     * @param filePath Path to the file duke.txt.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads file at filePath.
     * @return Scanner sc
     */
    public Scanner load() {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            return sc;
        } catch (FileNotFoundException fileError) {
            Ui.showError("Error in loading data. File not found.");
            return new Scanner("");
        }
    }

    /**
     * Writes content to file at filePath.
     * @param it Iterator of the arrayList from TaskList.
     */
    public String save(Iterator<Task> it) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            while (it.hasNext()) {
                fw.write(it.next().toStringSaveFormat());
            }
            fw.close();
            return "Successfully saved contents into duke.txt";
        } catch (IOException e) {
            return "Error in saving data.";
        }
    }
}
