package storage;


import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.DukeException;
import exceptions.StorageException;



/**
 * Class for persistent storage using text file
 */
public class Storage {
    private static final Path PATH = Path.of("data", "store.txt");

    /**
     * Initialise Storage class by creating relevant directories and files if not already there
     */
    public Storage() throws StorageException {
        try {
            Files.createDirectories(PATH.getParent());
            Files.createFile(PATH);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Initialising existing task list...");
        } catch (IOException e) {
            throw new StorageException("There was an unrecognised error when initialising storage");
        }
    }

    /**
     * Persistently store a list of strings
     * @param list List of strings to store
     */
    public void store(List<String> list) throws DukeException {
        try {
            Files.write(PATH, list);
        } catch (IOException e) {
            throw new DukeException("There was an unrecognised error when storing tasks");
        }
    }

    /**
     * Return the list of strings persistently stored - empty if nothing has been stored
     * @return List of strings stored
     */
    public List<String> load() throws StorageException {
        try {
            return Files.lines(PATH)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("There was an unrecognised error when loading tasks");
        }
    }
}
