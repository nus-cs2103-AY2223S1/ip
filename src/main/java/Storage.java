import java.io.*;
import java.util.ArrayList;
import java.nio.file.Path;

public class Storage {
    private static ArrayList<ListObject> tasksStored;

    public Storage(String filePath){
        this.tasksStored = readFromFile(filePath);
    }

    private static ArrayList<ListObject> readFromFile(String path){
        ArrayList<ListObject> inList = new ArrayList<>();
        try {
            File listFile = new File(path);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(listFile));
            inList = (ArrayList<ListObject>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            return inList;
        }
    }

    public static void makeListFile(String path, ArrayList<ListObject> lst){
        try {
            File listFile = new File(path);
            //adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(listFile));
            out.writeObject(lst);
            out.close();
        }
        catch(IOException e){
            System.out.println(e.toString());
        }

    }


}