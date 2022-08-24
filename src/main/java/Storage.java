import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Storage {
    private static final String DIRECTORY = System.getProperty("user.home") + "/Duke";
    private static final String FILE_PATH = DIRECTORY + "/Duke.txt";

    public Storage(String filePath) throws DukeException{
        try {
            File parentDirectory = new File(DIRECTORY);

            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }

            File dukeFile = new File(FILE_PATH);

            if(!dukeFile.exists()) {
                dukeFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Ugh. I cannot seem to create a file for you ...");
        }
    }

    public void save(ArrayList<Task> taskItems) throws DukeException {
        try {
            String taskList = Ui.getIndexedListForViewing(taskItems);
            Files.write(path, Collections.singleton(taskList));
        } catch (IOException ioe) {
            throw new DukeException("Error writing to file: " + path);
        }
    }
    public Duke load() throws StorageOperationException {

        try {
            return AddressBookDecoder.decodeAddressBook(Files.readAllLines(path));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
            // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    public String getPath() {
        return path.toString();
    }

    /* Note: Note the use of nested classes below.
     * More info https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

}
