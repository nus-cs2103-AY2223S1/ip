package duke;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates writing to and reading from Duke's save file.
 */
public class Storage {
    /** Contains the path to Duke's save file. */
    private String filePath;

    /**
     * Constructor for Storage.
     * @param filePath Path to Duke's save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Writes the list of tasks into a save file.
     * @param inputList Tasks to be written to file
     * @throws DukeException if error occurs while writing to file
     */
    public void write(String[] inputList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (String task : inputList) {
                fw.write(task.strip());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException err) {
            throw new DukeException("Error occurred while writing to file.");
        }
    }

    /**
     * Reads all tasks from save file.
     * @return ArrayList containing each task in a String array
     * @throws DukeException if error occurs while reading from file
     */
    public ArrayList<String[]> load() throws DukeException {
        ArrayList<String[]> db = new ArrayList<>(10);

        try {
            Scanner s = new Scanner(new File(filePath));
            while (s.hasNext()) {
                String[] tmp = s.nextLine().split("\\|");
                for (int i = 0; i < tmp.length; i++) {
                    tmp[i] = tmp[i].strip();
                }
                db.add(tmp);
            }
            s.close();

            return db;
        } catch (IOException err) {
            throw new DukeException("Unable to load tasks from file.");
        }
    }
}
