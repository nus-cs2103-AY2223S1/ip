package Duke.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Duke.Tasks.TaskList;

/**
 * Class that provides methods to save all data.
 */
public class FileSaver {

    private Path filePath;

    /**
     * Public constructor of FileSaver Class.
     * Sets up the file path, and checks whether the file exists.
     * @param inputFileName File name to save data into.
     * @throws IOException Exception may occur when creating new file.
     */
    public FileSaver (String inputFileName) throws IOException {
        String fileName = inputFileName + ".txt";
        File dukeFile = new File("data", fileName);
        if (!dukeFile.exists()) {
            dukeFile.createNewFile();
        }
        this.filePath = Paths.get(String.valueOf(dukeFile));
    }

    /**
     * Saves all the tasks.
     * @param tasks TaskList containing all tasks.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.filePath.toString());
            writer.write(tasks.save());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
