package duke;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private static final String DIRECTORY_NAME = "SavedData";
    private static final String FILE_NAME = "SavedData/SaveData.txt";

    /**
     * Takes in a list of items
     * and saves it to a binary file
     * @param saveItems
     */
    public static void save(List<Task> saveItems) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(saveItems);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot save, File not found");
        } catch (IOException e) {
            System.out.println("Cannot Initialize Stream");
        }

    }
    /**
     * Takes in a list of items
     * and saves it to a binary file
     * @return List<Task> saveItems
     * @throws FileNotFoundException if the file/path has not been created or initalized
     * @throws IOException if the file is unable to read for any other reason
     */
    public static List<Task> readItems() {
        //1) Create file
        try {
            File directory = new File(DIRECTORY_NAME);
            directory.mkdir();
            File f = new File(FILE_NAME);
            boolean createNewSaveFile = f.createNewFile();
            if (createNewSaveFile) {
                System.out.println("File doesn't exist, creating one");
            }
        } catch (IOException exp) {
            System.out.println("IOException occurred");
            exp.printStackTrace();

        }

        try {

            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> readItems = (ArrayList<Task>) ois.readObject();
            ois.close();
            return readItems;


        } catch (FileNotFoundException e) {
            return new ArrayList<>(); //empty Task list for initial initialization
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }
        return null;
    }

    public static TaskList load() {
        List<Task> currItems = readItems();
        return new TaskList(currItems);

    }

}
