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
    private final StorageReader storageReader;
    private final StorageWriter storageWriter;

    /**
     * Constructor.
     * @param filePath String representation of relative file path.
     */
    public Storage(String filePath) {
        createFileIfDoesntExist(filePath);
        this.storageWriter = new StorageWriter(path);
        this.storageReader = new StorageReader(path);
    }

    /**
     * Gets location of disk storage file.
     * @throws DukeException when Duke program run outside of Duke folder.
     */
    private void getPath(String pathString) throws DukeException {
        String currPath = System.getProperty("user.dir");
        if (currPath.contains("ip")) {
            this.path = Paths.get(currPath, "src", "main", "java", pathString);
        } else {
            throw new DukeException("Cannot run from outside of ip folder of Duke");
        }
    }

    /**
     * Creates file at path for disk storage.
     */
    public void createFileIfDoesntExist(String location) {
        try {
            getPath(location);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e);
        }
    }

    //Define all StorageWriter methods

    public boolean appendLine(String s) {
        return storageWriter.appendLine(s);
    }

    public boolean deleteLine(int index) {
        return storageWriter.deleteLine(index);
    }

    public boolean changeLine(int index, String newString) {
        return storageWriter.changeLine(index, newString);
    }

    //Define all StorageReader methods

    public TaskList syncArrayList() throws DukeException {
        return storageReader.syncArrayList();
    }

    public List<String> getAllLines() {
        return storageReader.getAllLines();
    }
}
