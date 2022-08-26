package duke.util;

import duke.exception.DukeIoException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private static final String FILE_IO_ERROR_MESSAGE = "Oops! Something went wrong when saving the file!";

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @param
     * @param
     * @param
     * @param
     * @param
     * @return
     * @throws
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
