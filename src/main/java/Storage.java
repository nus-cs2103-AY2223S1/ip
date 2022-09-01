import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Class used to store and load all your tasks from your local storage.
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public class Storage {
    private static final String STORAGE_PATH = "storage/storage.txt";

    /**
     * Method used to store tasks to the device local storage
     *
     * @param allTasks the list of tasks to be saved
     */
    public static void storeTasks(AllTasksList allTasks) {
        try {
            FileOutputStream fileOut = new FileOutputStream(Storage.STORAGE_PATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(allTasks);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to retrieve the tasks stored on the device
     *
     * @return the list of tasks saved on the device
     */
    public static AllTasksList loadTasks() {
        AllTasksList allTasks = null;
        try {
            FileInputStream fileIn = new FileInputStream(Storage.STORAGE_PATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allTasks = (AllTasksList) in.readObject();
            in.close();
            fileIn.close();
            return allTasks;
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (allTasks == null) {
                allTasks = new AllTasksList();
            }
            return allTasks;
        }
    }
}
