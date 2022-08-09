import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class SaveData {
    private static final String FILENAME = "SaveData.txt";

    public static void Save(List<Task> saveItems) {
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
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
        try {

            FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> readItems = (ArrayList<Task>) ois.readObject();
            ois.close();
            return readItems;



        } catch (FileNotFoundException e) {
            return new ArrayList<Task>(); //empty Task list for initial initialization
        } catch (IOException e) {
            System.out.println("Cannot Initialize Stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }
        return null;
    }


}
