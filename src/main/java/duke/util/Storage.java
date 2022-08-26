package duke.util;

import duke.exception.DukeIoException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is responsible for file saving. It plays a database-like role.
 */
public class Storage {

    private static final String FILE_IO_ERROR_MESSAGE = "Oops! Something went wrong when saving the file!";

    private final String filePath;

    /**
     * The standard constructor of the class.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Deserializes the TaskList and saves it as plain text.
     *
     * @param fileStream The string to be saved in the specified path.
     * @throws DukeIoException This exception occurs when the file cannot be created or over-written.
     */
    public void saveFile(String fileStream) throws DukeIoException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new DukeIoException(FILE_IO_ERROR_MESSAGE);
                }
            }

            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(fileStream, 0, fileStream.length());
            fileWriter.close();
        } catch (IOException exception) {
            throw new DukeIoException(FILE_IO_ERROR_MESSAGE);
        }
    }
}
