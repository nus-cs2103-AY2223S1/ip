import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static ArrayList<ListObject> tasksStored;

    public Storage(){
        this.tasksStored = readFromFile();
    }

    private static ArrayList<ListObject> readFromFile(){
        ArrayList<ListObject> inList = new ArrayList<>();
        try {
            File listFile = new File("src\\main\\java\\DukeList.txt");
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

    public static void makeListFile(){
        try {
            ArrayList<ListObject> lst = tasksStored;
            File listFile = new File("src\\main\\java\\DukeList.txt");
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