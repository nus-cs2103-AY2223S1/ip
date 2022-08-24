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

    private static final String DEFAULT_STORAGE_FILE = "tasklist.txt";
    private static final String CURRENT_DIRECTORY = "user.dir";

    private Path path;


    public Storage() throws FileDoesNotExistException {
        String currentDir = System.getProperty(CURRENT_DIRECTORY);
        this.path = Paths.get(currentDir, DEFAULT_STORAGE_FILE);
    }

    public void save(TaskList taskList) throws StorageOperationException {
        List<String> encodedTasks = StorageEncoder.encode(taskList);

        try {
            PrintWriter printWriter = new PrintWriter(path.toFile());
            for (int i =0 ;i < encodedTasks.size(); i++) {
                printWriter.println(encodedTasks.get(i));
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            create();
        }

    }

    public TaskList load() throws DukeException {
        if (!Files.exists(path)) {
            return new TaskList();
        }
        return StorageDecoder.decode(path);
    }

    public void create() throws StorageOperationException {
        String currentDir = System.getProperty(CURRENT_DIRECTORY);
        Path newPath = Paths.get(currentDir,DEFAULT_STORAGE_FILE);
        File newFile = newPath.toFile();
        try {
            newFile.createNewFile();
            this.path = newPath;
        } catch (IOException e) {
            throw new StorageOperationException("Error creating file: " + path);
        }
    }

}
