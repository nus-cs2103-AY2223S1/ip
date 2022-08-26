package DukeProgram.Storage;

import Exceptions.KeyNotFoundException;

import java.io.*;

public class SaveManager {

    private static Storage dataInMemory;

    private static final String DATA_FOLDER = System.getProperty("user.home");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(
            DATA_FOLDER,
            "RUIHAN",
            "Duke"
    );

    public static void save(String header, Serializable obj) {
        System.out.println("SAVED " + header);
        dataInMemory.put(header, obj);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T load(String header) throws KeyNotFoundException {
        return (T)dataInMemory.get(header);
    }

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

    public static boolean wipeData() {
        File saveFile = new File(PATH.toString());
        return saveFile.delete();
    }
}
