package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.task.TaskList;

/**
 * Constructor for Storage class
 */
public class Storage {
    private final Path path;

    Storage(String filePath) {
        this.path = Paths.get(System.getProperty("user.dir"), filePath);
    }

    /**
     * Returns a new Storage instance based on given filepath
     * @param filePath
     * @return new Storage instance
     */
    public static Storage of(String filePath) {
        try {
            Storage newStorage = new Storage(filePath);
            Path storagePath = newStorage.path;

            if (!validPath(filePath)) {
                throw new InvalidStorageFilePathException(
                        "duke.task.Storage file needs to end with .txt, please try again.");
            }

            if (!Files.exists(storagePath) || !Files.isRegularFile(storagePath)) {
                // Create a new file
                Files.createFile(storagePath);
            }
            return new Storage(filePath);
        } catch (InvalidStorageFilePathException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            return new Storage(filePath);
        }
    }

    private static boolean validPath(String filePath) {
        return filePath.contains(".txt");
    }

    /**
     * Returns a TaskList instance from saved data contained in todolist.txt.
     * @return a TaskList instance with the loaded data from todolist.txt
     * @throws IOException
     * @throws StorageOperationException
     * @see TaskList
     */
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

    /**
     * Writes data to targetfile.txt from taskList and returns an instance of TaskList.
     * @param taskList
     * @return a tasklist if needed
     * @throws IOException
     * @see TaskList
     */
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
            String toWrite = sb.subSequence(0, sb.length() - 1).toString();
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
