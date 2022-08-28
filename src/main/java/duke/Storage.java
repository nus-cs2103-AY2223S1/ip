package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    // Exceptions not handled
    private static final String FILENAME = "SaveData.txt";

    /**
     * Writes the Tasks ArrayList input to the specified save file
     * @param  array Tasks ArrayList
     */
    public void save(ArrayList<Task> array) {
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

    /**
     * Returns a Tasks ArrayList retrieved from the specified save file
     * @return Tasks ArrayList
     */
    public ArrayList<Task> read() {
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
