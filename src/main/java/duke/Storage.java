package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents storage.
 */
public class Storage {

    private File file;
    private String directoryPath;
    private String filePath;
    private static final String FILE_NOT_FOUND_MESSAGE = "File could not be found";
    private static final String FILE_NOT_SAVED_MESSAGE = "File could not be saved";
    private static final String FILE_NOT_CREATED_MESSAGE = "File could not be created";

    /**
     * Constructs storage.
     *
     * @param filePath file path to store data.
     */
    Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        String [] directoryPaths = filePath.split("/");
        this.directoryPath = String.join("/", Arrays.copyOfRange(
                directoryPaths, 0, directoryPaths.length - 1));
    }

    /**
     * Loads existing file.
     *
     * @return data from file.
     * @throws DukeException if file does not exist.
     */
    protected String load() throws DukeException {
        try {
            Scanner s = new Scanner(this.file);
            StringBuilder data = new StringBuilder();
            while (s.hasNext()) {
                if (!data.toString().equals("")) {
                    data.append("\n");
                }
                String input = s.nextLine();
                data.append(input);
            }
            return data.toString();
        } catch (FileNotFoundException e) {
            throw new DukeException(FILE_NOT_FOUND_MESSAGE);
        }
    }

    /**
     * Saves data.
     *
     * @param data Data to be saved.
     * @throws DukeException if error in saving file.
     */
    public void save(String data) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(FILE_NOT_SAVED_MESSAGE);
        }
    }

    /**
     * Creates file.
     *
     * @throws DukeException if error in creating file.
     */
    protected void createFile() throws DukeException {
        try {
            File newDirectory = new File(directoryPath);
            newDirectory.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException(FILE_NOT_CREATED_MESSAGE);
        }
    }
}
