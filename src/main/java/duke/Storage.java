package duke;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/*
   Deals with Saving/Loading data
   Methods are mostly static
 */
public class Storage {

    private static final String DIRECTORY_NAME = "SavedData";
    private static final String FILE_NAME = "SavedData/SaveData.txt";

    public static void Save(List<Task> saveItems) {
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

    public static List<Task> ReadItems() {
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
        List<Task> currItems = ReadItems();
        return new TaskList(currItems);

    }

}
