package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Represents a storage object to handle I/O related operations.
 * Reads and writes to a file as defined by the filePath string.
 */
public class Storage {
    private final String filePath;

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
     * @param it Iterator of the ArrayList from TaskList.
     */
    public void save(Iterator<Task> it) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            while (it.hasNext()) {
                fw.write(it.next().toStringSaveFormat());
            }
            fw.close();
            Ui.showError("Successfully saved contents into duke.txt");
        } catch (IOException e) {
            Ui.showError("Error in saving data.");
        }
    }
}
