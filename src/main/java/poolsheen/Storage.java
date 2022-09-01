package poolsheen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.Scanner;

import poolsheen.task.Deadline;
import poolsheen.task.Event;
import poolsheen.task.Task;
import poolsheen.task.ToDo;

/**
 * Represents everything that deals with reading from and writing to files.
 */
public class Storage {
    /** The file path of the save file that the Storage object will work with. */
    private String filePath;

    /** The file object representing the save file which the Storage object will work with. */
    private File saveFile;

    /**
     * A public constructor to initialise a Storage object.
     *
     * @param filePath The path of the file which the Storage object will be interacting with.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.saveFile = new File(this.filePath);
        //Automatically creates a new save file
        try {
            if (this.saveFile.createNewFile()) {
                System.out.println("New Save File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("Error when constructing Storage object.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns an ArrayList of Tasks by parsing the contents of the save file.
     *
     * @throws IOException Thrown if there is an error with parsing the file.
     */
    public ArrayList<Task> load() throws PoolsheenException {
        ArrayList<Task> res = new ArrayList<>(100);
        try {
            Scanner s = new Scanner(this.saveFile);
            while (s.hasNextLine()) {
                String[] arr = s.nextLine().split("\\|");
                ArrayList<String> arl = new ArrayList<>();
                for (String str : arr) {
                    arl.add(str);
                }
                Predicate<String> pred = x -> x.equals("");
                arl.removeIf(pred);
                //Uncomment to see how the file contents are parsed as arrays.
//                System.out.println(arl.toString());
                String taskType = arl.get(1);
                boolean isDone;
                if (arl.get(2).equals("X")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String taskDesc;
                String time;
                switch (taskType.toUpperCase()) {
                case "T":
                    taskDesc = arl.get(3);
                    res.add(new ToDo(taskDesc, isDone));
                    break;
                case "D":
                    if (arl.size() != 5) {
                        throw new PoolsheenException("Error with Deadline Task");
                    }
                    taskDesc = arl.get(3);
                    time = arl.get(4);
                    res.add(new Deadline(taskDesc, isDone, time));
                    break;
                case "E":
                    if (arl.size() != 5) {
                        throw new PoolsheenException("Error with Event Task");
                    }
                    taskDesc = arl.get(3);
                    time = arl.get(4);
                    res.add(new Event(taskDesc, isDone, time));
                    break;
                default:
                    throw new PoolsheenException("Error with loading");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find save file while loading\n" + e.getMessage());
        }
        return res;
    }

    /**
     * Updates the contents of the save file.
     *
     * @param tl The ArrayList of Tasks to refer to when updating.
     */
    public void update(TaskList tl) throws IOException{
        FileWriter fw = new FileWriter(this.filePath);
        String str = "";
        int max = tl.getSize();
        for (int pos = 0 ; pos < max; pos++) {
            Task t = tl.get(pos);
            int listPos = pos + 1;
            str += listPos + "|" + String.join("|" , t.toArr());
            if (pos < max) {
                str += "\n";
            }
        }
        fw.write(str);
        fw.close();
    }
}
