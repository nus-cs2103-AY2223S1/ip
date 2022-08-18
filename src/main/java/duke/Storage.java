package duke;

import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    public static final String DEFAULT_FILE_NAME = "todolist.txt";
    public final Path path;

    Storage() throws InvalidStorageFilePathException, IOException {
        this(DEFAULT_FILE_NAME);
    }

    Storage(String filePath) throws InvalidStorageFilePathException, IOException {
        this.path = Paths.get(System.getProperty("user.dir"), filePath);
        if (!validPath(filePath)) {
            throw new InvalidStorageFilePathException("duke.task.Storage file needs to end with .txt, please try again.");
        }
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            // Create a new file
            Files.createFile(path);
        }
    }

    private boolean validPath(String filePath) {
        return filePath.contains(".txt");
    }

    public TaskList loadSavedData() throws IOException, StorageOperationException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            // Create a new file
            Files.createFile(path);
        }

        try {
            List<String> fileLines = Files.readAllLines(path);
            return StorageParser.parseFile(fileLines);
        } catch (IOException e) {
            throw new StorageOperationException("Error parsing file @ " + path);
        } catch (Exception e) {
            System.out.println(e);
            return new TaskList();
        }
    }

    public TaskList writeDataToFile(TaskList taskList) throws IOException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            // Create a new file
            Files.createFile(path);
        }

        try {
            if (taskList.getTaskList().size() == 0) {
                Files.write(this.path, new byte[0]);
                return taskList;
            }
            StringBuilder sb = new StringBuilder();
            taskList.getTaskList().forEach((task) -> {
                sb.append(StorageWriter.writeSingleTask(task) + "\n");
            });
            String toWrite = sb.subSequence(0, sb.length()-1).toString();
            byte[] byteString = toWrite.getBytes();
            Files.write(this.path, byteString);
            return taskList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return taskList;
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends Exception {
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
