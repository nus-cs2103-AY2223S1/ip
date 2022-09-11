package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/** Represents a Storage class. Handles reading and writing to a Save File */
public class Storage {
    // Exceptions not handled
    private final String filename;
    private static final ArrayList<Task> SAMPLETASKS = new ArrayList<Task>();

    public Storage(String filename) {
        this.filename = filename;
    }

    /**
     * Writes the Tasks ArrayList input to the specified save file
     * @param  array Tasks ArrayList
     */
    public void save(ArrayList<Task> array) {
        try {
            FileOutputStream writeData = new FileOutputStream(filename);
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
    @SuppressWarnings("unchecked")
    public ArrayList<Task> read() {
        try {
            FileInputStream readData = new FileInputStream(filename);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            Object storedObject = readStream.readObject();
            assert storedObject.getClass() == SAMPLETASKS.getClass();
            ArrayList<Task> returnVal = (ArrayList<Task>) storedObject;
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
