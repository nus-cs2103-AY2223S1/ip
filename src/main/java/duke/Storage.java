package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import duke.exception.DukeException;

/**
 * Deals with loading and saving data to the hard disk
 */
public class Storage {
    private File DIRECTORY;
    private String FILEPATH;

    /**
     * Constructs a storage to be managed at the specified file location.
     *
     * @param directoryPath The path of the directory containing the file
     * @param filename The filename of the file to save the data to
     */
    public Storage(String directoryPath, String filename) {
        this.DIRECTORY = new File(directoryPath);
        this.FILEPATH = directoryPath + "/" + filename;
    }

    /**
     * Retrieves the file the storage saves the data to
     *
     * @return The file that the storage saves the data to
     */
    public File retrieveFile() {
        return new File(this.FILEPATH);
    }

    /**
     * Writes and saves the file based on the data input.
     *
     * @param fileData The contents of the file to be saved
     * @throws DukeException If the file could not be saved
     */
    public void writeToFile(String fileData) throws DukeException {
        if (!this.DIRECTORY.exists()) {
            this.DIRECTORY.mkdir();
        }

        try {
            FileWriter fw = new FileWriter(this.FILEPATH);
            fw.write(fileData);
            fw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Duke could not create a file to save your tasks :(");
        } catch (IOException e) {
            throw new DukeException(String.format("Duke had some trouble saving your file: %s", e.getMessage()));
        }
    }
}
