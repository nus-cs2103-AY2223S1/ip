import java.io.File;
import java.io.IOException;

public class Storage {
    private final File dataFile;

    Storage(String filePath) {
        dataFile = new File(filePath);
    }

    public void save(TaskList taskList) {
        try {
            FileEncoder.encodeFile(dataFile, taskList);
        } catch (IOException ioe) {
            System.err.println("Warning! Errors encountered when writing to file. " +
                    "Your data might not be saved.");
        }
    }

    public TaskList load() throws IOException {
        dataFile.createNewFile(); //temporary
        return FileDecoder.decodeFile(dataFile);
    }
}
