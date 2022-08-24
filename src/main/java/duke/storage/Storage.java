package duke.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.FileDoesNotExistException;
import duke.exception.StorageOperationException;
import duke.task.TaskList;

public class Storage {

    public static final String DEFAULT_STORAGE_FILE = "tasklist.txt";
    public static final String DEFAULT_STORAGE_SOURCE_FOLDER = "src";
    public static final String DEFAULT_STORAGE_DUKE_FOLDER = "duke";
    public static final String CURRENT_DIRECTORY = "user.dir";

    public final Path path;


    public Storage() throws FileDoesNotExistException {
        String currentDir = System.getProperty(CURRENT_DIRECTORY);
        this.path = Paths.get(currentDir, DEFAULT_STORAGE_SOURCE_FOLDER, DEFAULT_STORAGE_DUKE_FOLDER,DEFAULT_STORAGE_FILE);
    }

    public void save(TaskList taskList) throws FileDoesNotExistException {
        List<String> encodedTasks = StorageEncoder.encode(taskList);

        try {
            PrintWriter printWriter = new PrintWriter(path.toFile());
            for (int i =0 ;i < encodedTasks.size(); i++) {
                printWriter.println(encodedTasks.get(i));
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new FileDoesNotExistException();
        }

    }

    public TaskList load() throws DukeException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }
        return StorageDecoder.decode(path);
    }

}
