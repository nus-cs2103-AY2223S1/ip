package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import exceptions.DukeException;
import tasklist.TaskList;


/**
 * Class representing disk storage used in Duke
 * to backup task list.
 * Requires valid file parh to be set at
 * each run of Duke.
 */
public class Storage {
    private Path path;
    private final StorageReader STORAGE_READER;
    private final StorageWriter STORAGE_WRITER;

    /**
     * Constructor.
     * @param filePath String representation of relative file path.
     */
    public Storage(String filePath) {
        createFileIfDoesntExist(filePath);
        this.STORAGE_WRITER = new StorageWriter(path);
        this.STORAGE_READER = new StorageReader(path);
    }

    /**
     * Gets location of disk storage file.
     * @throws DukeException when Duke program run outside of Duke folder.
     */
    private void getPath(String pathString) throws DukeException, IOException {
        String currPath = System.getProperty("user.dir");
        String[] pathElements = pathString.split("/");
        Path tempPath;
        for(String s: pathElements) {
            currPath += "\\" + s;
            tempPath = Path.of(currPath);
            if (isFileExtension(s)){
                if (!Files.exists(tempPath)) {
                    Files.createFile(tempPath);
                }
                this.path =  tempPath;
                break;
            } else if (isFolderExtension(s)) {
                if (!Files.exists(tempPath)) {
                    Files.createDirectory(tempPath);
                }
            }
        }
    }

    private static boolean isFileExtension(String extension) {
        return extension.contains(".");
    }

    private static boolean isFolderExtension(String extension) {
        return true;
    }

    /**
     * Creates file at path for disk storage.
     */
    public void createFileIfDoesntExist(String location) {
        try {
            getPath(location);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e);
        }
    }

    //Define all StorageWriter methods

    public boolean appendLine(String s) {
        return STORAGE_WRITER.appendLine(s);
    }

    public boolean deleteLine(int index) {
        return STORAGE_WRITER.deleteLine(index);
    }

    public boolean changeLine(int index, String newString) {
        return STORAGE_WRITER.changeLine(index, newString);
    }

    //Define all StorageReader methods

    public TaskList syncArrayList() throws DukeException {
        return STORAGE_READER.syncArrayList();
    }
<<<<<<< HEAD

    public List<String> getAllLines() {
        return STORAGE_READER.getAllLines();
    }
=======
>>>>>>> master
}
