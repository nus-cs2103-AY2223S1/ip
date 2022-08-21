import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class Storage {
    private Path path;
    private StorageReader storageReader;
    private StorageWriter storageWriter;

    public Storage() {
        createFileIfDoesntExist();
        this.storageWriter = new StorageWriter(path);
        this.storageReader = new StorageReader(path);
    }
    /**
     * Gets location of disk storage file.
     * @throws DukeException when Duke program run outside of Duke folder.
     */
    private void getPath() throws DukeException {
        String pathString = System.getProperty("user.dir");
        if (pathString.contains("ip")) {
            pathString = pathString.substring(0, pathString.indexOf("ip") + 2);
            this.path = Paths.get(pathString, "src", "main", "java", "userinputhistory.txt");
        } else {
            throw new DukeException("Cannot run from outside of ip folder of Duke");
        }
    }

    /**
     * Creates file at path for disk storage.
     */
    public void createFileIfDoesntExist() {
        try {
            getPath();
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e);
        }
    }

    //Define all StorageReader methods
    public Task fileLineToTask(String line) throws DukeException {
        return storageReader.fileLineToTask(line);
    }
    public LocalDate getDateFromStorage(String line, String compareString) throws DukeException {
        return storageReader.getDateFromStorage(line, compareString);
    }
    public List<String> getAllLines() {
        return storageReader.getAllLines();
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
}
