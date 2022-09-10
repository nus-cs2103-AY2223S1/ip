package duke.storage;

import duke.task.TaskList;

import java.io.File;
import java.io.IOException;

public class Storage {
    private final File dataFile;

    public Storage(String filePath) {
        dataFile = new File(filePath);
    }

    /**
     * Encode the task list and store in specified file path.
     *
     * @param taskList Task list to be saved.
     */
    public void save(TaskList taskList) {
        try {
            FileEncoder.encodeFile(dataFile, taskList);
        } catch (IOException ioe) {
            System.err.println("Warning! Errors encountered when writing to file. " +
                    "Your data might not be saved.");
        }
    }

    /**
     * Decode the task list from specified file path, if exists. Else, create a new file.
     *
     * @return Task list containing tasks.
     * @throws IOException If error occurs when trying to read from file.
     */
    public TaskList load() throws IOException {
        dataFile.createNewFile(); //temporary
        return FileDecoder.decodeFile(dataFile);
    }
}
