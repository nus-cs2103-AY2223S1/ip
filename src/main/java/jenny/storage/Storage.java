package jenny.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jenny.exceptions.JennyException;


/**
 * Abstract class for all other different types of storage.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @param <T> the type of item to store.
 * @author Deon
 */
public abstract class Storage<T> {
    private static final String MESSAGE_SCOPE = Storage.class.getSimpleName();
    private final Path storagePath;

    /**
     * Constructor for an instance of a new storage.
     * Will initialise folders at the default home location {@code [user.home]},
     * under the provided {@code folderName} {@code [jenny.storage.folderName]}.
     *
     * @param folderName the name of the storage folder.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    public Storage(String folderName) throws JennyException {
        String userHome = System.getProperty("user.home");
        this.storagePath = Paths.get(userHome, ".jenny", "storage", folderName);
        try {
            Files.createDirectories(storagePath);
        } catch (UnsupportedOperationException | SecurityException | IOException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }


    /**
     * Save the item to storage.
     *
     * @param t the item to store
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    public abstract void save(T t) throws JennyException;


    /**
     * Load the item from storage.
     *
     * @return the item to load
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    public abstract T load() throws JennyException;

    /**
     * {@inheritDoc}
     *
     * @return the string representation of the path to the folder.
     */
    @Override
    public String toString() {
        return storagePath.toString();
    }
}
