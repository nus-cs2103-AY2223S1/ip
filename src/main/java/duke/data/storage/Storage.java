package duke.data.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A generic storage class to serialize and deserialize objects
 *
 * @param <T> a serializable object type
 */
public class Storage<T extends Serializable> {

    private final Path storagePath;

    /**
     * Constructor for Storage class
     *
     * @param storagePath path to storage file
     */
    public Storage(Path storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Serializes object to specified storage file.
     *
     * @param object object to serialize.
     * @throws StorageException if serialization fails due to IO error.
     */
    public void save(T object) throws StorageException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(storagePath.toString());
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(object);
            objOutputStream.close();
            fileOutputStream.close();
        } catch (IOException saveError) {
            throw new StorageException(String.join("\n", "Failed to save to storage!",
                String.format("Storage Path: %s", storagePath)));
        }
    }

    /**
     * Deserializes object from specified storage file.
     *
     * @param fallback object to return if storage has not been initialized.
     * @return deserialized object of type T.
     * @throws StorageException if deserialization fails due to IO error.
     */
    @SuppressWarnings("unchecked")
    public T load(T fallback) throws StorageException {
        if (!Files.exists(storagePath)) {
            return fallback;
        }

        T loadedObject;
        try (
            FileInputStream fileInputStream = new FileInputStream(storagePath.toString());
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
        ) {
            loadedObject = (T) objInputStream.readObject();
        } catch (IOException | ClassNotFoundException loadError) {
            throw new StorageException(String.join("\n", "Failed to load from storage!",
                String.format("Storage Path: %s", storagePath)));
        }
        return loadedObject;
    }
}
