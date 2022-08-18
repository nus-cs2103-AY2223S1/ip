package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StorageHandler {
    public static final String DEFAULT_FILE_NAME = "todolist.txt";
    public final Path path;

    StorageHandler() throws InvalidStorageFilePathException, IOException {
        this(DEFAULT_FILE_NAME);
    }

    StorageHandler(String filePath) throws InvalidStorageFilePathException, IOException {
        this.path = Paths.get(System.getProperty("user.dir"), filePath);
        if (!validPath(filePath)) {
            throw new InvalidStorageFilePathException("duke.Storage file needs to end with .txt, please try again.");
        }
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            // Create a new file
            Files.createFile(path);
        }
    }

    private boolean validPath(String filePath) {
        return filePath.contains(".txt");
    }

    public Storage loadSavedData() throws IOException, StorageOperationException {
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
            return new Storage();
        }
    }

    public Storage writeDataToFile(Storage storage) throws IOException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            // Create a new file
            Files.createFile(path);
        }

        try {
            StringBuilder sb = new StringBuilder();
            storage.taskList.forEach((task) -> {
                sb.append(StorageWriter.writeSingleTask(task) + "\n");
            });
            String toWrite = sb.subSequence(0, sb.length()-1).toString();
            byte[] byteString = toWrite.getBytes();
            Files.write(this.path, byteString);
            return storage;
        } catch (Exception e) {
            System.out.println(e);
        }
        return storage;
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
