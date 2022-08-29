package dukeprogram.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import dukeprogram.Duke;
import exceptions.KeyNotFoundException;
import utilities.SerializedNamesFormatter;

/**
 * SaveManager manages all the save functionalities
 */
public class SaveManager {

    private static Storage dataInMemory;

    private static final String DATA_FOLDER = System.getProperty("user.home");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(
            DATA_FOLDER,
            "RUIHAN",
            "Duke"
    );

    /**
     * Saves to the storage object
     * @param header the name of the variable to associate the saved object with
     * @param obj the saved object
     */
    public static void save(String header, Serializable obj) {
        dataInMemory.put(header, obj);
    }

    /**
     * Loads to the storage object
     * @param header the name of the variable to associate the saved object with
     * @return a Serializable object if possible
     * @throws KeyNotFoundException if no such header name exists
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T load(String header) throws KeyNotFoundException {
        return (T) dataInMemory.get(header);
    }

    /**
     * Serializes a file to disk under the path
     * @param fileName the file name to serialize to under the folder path
     */
    public static void serialize(String fileName) throws IOException {
        FileOutputStream fileOutputStream;
        File saveFile = new File(PATH.toString(), fileName);

        saveFile.getParentFile().mkdirs();
        fileOutputStream = new FileOutputStream(saveFile);

        ObjectOutputStream objOutputStream = new ObjectOutputStream(
                fileOutputStream
        );

        objOutputStream.writeObject(dataInMemory);
        objOutputStream.flush();
        objOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Deserializes a file from disk under the path
     * @param fileName the file name to deserialize from
     * @return whether deserialization was successful
     */
    public static boolean deserialize(String fileName) {
        try {
            File file = new File(PATH.toString(), fileName);

            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(fileInputStream);
            ObjectInputStream objInputStream = new ObjectInputStream(
                    fileInputStream
            );

            try {
                dataInMemory = (Storage) objInputStream.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("FATAL ERROR: " + e.getMessage());
            }

            fileInputStream.close();
            return true;
        } catch (IOException e) {
            dataInMemory = new Storage();
            return false;
        }
    }

    /**
     * Erases all the data of this current save file.
     * @return
     */
    public static boolean wipeData() {
        File saveFile = new File(PATH.toString(),
                SerializedNamesFormatter.createFileNameForUser(Duke.getUser().getName()));
        return saveFile.delete();
    }
}
