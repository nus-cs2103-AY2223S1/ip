package jenny.storage;

import jenny.exceptions.JennyException;
import jenny.util.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Serves as a base class for different types of storage.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @param <T> the type of item to process.
 * @author Deon
 */
public abstract class AbstractStorage<T> {
    private static final String ERROR_CREATING_DIRECTORY = "Could not initialise the storage!";
    private static final String DIRECTORY_ALREADY_EXISTS = "Existing storage location found!";
    private final Path storagePath;

    /**
     * Initialise a storage at the default home location under the provided name.
     *
     * @param folderName name of the folder in storage.
     */
    protected AbstractStorage(String folderName) {
        String userHome = System.getProperty("user.home");
        this.storagePath = Paths.get(userHome, ".jenny", "storage", folderName);

        boolean directoryExists = Files.exists(this.storagePath);
        if (!directoryExists) {
            try {
                Files.createDirectories(storagePath);
            } catch (IOException e) {
                throw new JennyException(this.getClass().getSimpleName(), ERROR_CREATING_DIRECTORY);
            }
        } else {
            throw new JennyException(this.getClass().getSimpleName(), DIRECTORY_ALREADY_EXISTS);
        }
    }

    /**
     * Saves the item to storage.
     *
     * @param t the type of item to process.
     */
    public abstract void save(T t) throws JennyException;

    /**
     * Loads the item from storage.
     *
     * @return the item from storage.
     */
    public abstract T load() throws JennyException ;

    /**
     * {@inheritDoc}
     *
     * @return the path to the storage.
     */
    @Override
    public String toString() {
        return storagePath.toString();
    }
}
