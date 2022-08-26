package duke.util;

import duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * For saving and loading the task list to and from file.
 */
public class Storage {
    protected final String FILE_PATH;

    public Storage(String path) {
        this.FILE_PATH = path;
    }

    public ArrayList<String> load() throws FileNotFoundException{
        ArrayList<String> output = new ArrayList<>();
        File dataFile = new File(FILE_PATH);
        if (dataFile.exists()) {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String item = s.nextLine();
                output.add(item);
            }
        }
        return output;
    }

    public void saveToFile(TaskList list) {
        File directory = new File("data");
        File save = new File(FILE_PATH);
        directory.mkdir();
        try {
            save.createNewFile();
        } catch (IOException f) {
            System.out.println("something went wrong while creating save file\n" + f);
        }
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task item : list.getArray()) {
                fw.write(item.format() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("something went wrong while writing to save file\n" + e);
        }
    }
}
