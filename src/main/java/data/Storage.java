package data;

import data.tasks.StorageException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage<T extends Serializable> {

    private final Path storagePath;

    public Storage(Path storagePath) {
        this.storagePath = storagePath;
    }

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
