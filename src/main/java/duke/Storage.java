package duke;

import duke.task.Task;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Storage allows user store their current data after exiting the program
 */
public class Storage {
    private final File f;

    /**
     * Constructor for {@code Storage}
     * @param filePath the path of the file
     */
    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    /**
     * To check if file mentions in filePath exists
     * @return true if file exists and false if it does not
     */
    public boolean isFileExists() {
        return f.exists();
    }

    /**
     * To create a new file if the file does not exist
     */
    public void createNewFile() {
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Fail to create new file!");
        }
    }

    /**
     * To update the data in the file after certain changes has been made
     * @param tasks the latest data to be read into the file
     */
    public void update(ArrayList<Task> tasks) {
        try (FileOutputStream fos = new FileOutputStream(f);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(tasks);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * To load the data in the file when the program starts
     * @return an {@code ArrayList} that contains all the data from the file
     */
    public ArrayList<Task> load() {
        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            @SuppressWarnings("unchecked")
            ArrayList<Task> temp = (ArrayList<Task>) ois.readObject();
            return temp;
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}