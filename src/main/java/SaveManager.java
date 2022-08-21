import java.io.*;

import java.util.ArrayList;

public class SaveManager {
    private static final String FILENAME = "SaveData.txt";

    public static void Save(ArrayList<Task> array) {
        try {
            FileOutputStream writeData = new FileOutputStream(FILENAME);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(array);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Task> Read() {
        try {
            FileInputStream readData = new FileInputStream(FILENAME);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> returnVal = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return returnVal;
        } catch (FileNotFoundException e) {
            return new ArrayList<Task>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
