package DukeProgram;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveManager {

    private static DataInMemory dataInMemory;

    private static final String DATA_FOLDER = System.getProperty("user.home");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(
            DATA_FOLDER,
            "RUIHAN",
            "Duke",
            "Duke_SavedData"
    );

    public static void save(String header, Serializable obj) {
        dataInMemory.put(header, obj);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T load(String header) throws KeyNotFoundException {
        return (T)dataInMemory.get(header);
    }

    public static void serialize() throws IOException {
        FileOutputStream fileOutputStream;
        File saveFile = new File(PATH.toString());

        if (saveFile.getParentFile().mkdirs()) {
            fileOutputStream = new FileOutputStream(saveFile);
        } else {
            fileOutputStream = new FileOutputStream(PATH.toString());
        }

        ObjectOutputStream objOutputStream = new ObjectOutputStream(
                fileOutputStream
        );

        objOutputStream.writeObject(dataInMemory);
        objOutputStream.flush();
        objOutputStream.close();
        fileOutputStream.close();
    }

    public static boolean deserialize() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH.toString());
            ObjectInputStream objInputStream = new ObjectInputStream(
                    fileInputStream
            );

            try {
                dataInMemory = (DataInMemory) objInputStream.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("FATAL ERROR: " + e.getMessage());
            }

            fileInputStream.close();
            return true;
        } catch (IOException e) {
            dataInMemory = new DataInMemory();
            return false;
        }
    }

    public static boolean wipeData() {
        File saveFile = new File(PATH.toString());
        return saveFile.delete();
    }
}
