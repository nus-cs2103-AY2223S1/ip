package DukeProgram.Storage;

import DukeProgram.Duke;
import Exceptions.KeyNotFoundException;
import Utilities.SerializedNamesFormatter;
import Utilities.StringUtilities;

import java.io.*;

/**
 * The SaveManager is responsible for the operations of
 * serializing and deserializing Storage objects from disk and saving and loading
 * data into the Storage object.
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
     * Saves a serializable object to the current storage, associated by the header name
     * @param header the name to associate the object with
     * @param obj the object to store
     */
    public static void save(String header, Serializable obj) {
        dataInMemory.put(header, obj);
    }

    /**
     * Loads a Serializable object from the Storage object
     * @param header the associated name of the object to load
     * @param <T> the type of the object being loaded
     * @return a Serializable object of type T that was loaded
     * @throws KeyNotFoundException if the header name could not be associated with any object
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T load(String header) throws KeyNotFoundException {
        return (T)dataInMemory.get(header);
    }

    /**
     * Writes the Storage object to a file on disk in user's HOME folder.
     * Folders will be created if they do not exist. If the file does not exist,
     * a new file will be created, otherwise, the current file with the same location
     * and file name will be overwritten.
     * @param fileName the actual file name to write to
     * @throws IOException if opening and reading from file or object streams were not successful
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
     * Reads the object data from the given fileName and stores it as the Storage object
     * @param fileName the actual file name to read from, within the PATH
     * @return true if deserialization was successful, otherwise returns false
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
     * Destroys the save file of the current Storage object and wipes all data
     * @return true if the deletion was successful, otherwise false
     */
    public static boolean wipeData() {
        File saveFile = new File(PATH.toString(),
                SerializedNamesFormatter.createFileNameForUser(Duke.getUser().getName())
        );

        return saveFile.delete();
    }
}
