package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Class that contains methods to manipulate the list of tasks
 */
public class Storage {

    private final String path;

    /**
     * Constructor for Storage
     * @param path file path to from root directory to file
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads list of tasks in storage file
     * @return List of tasks
     */
    public ArrayList<String> load() {
        ArrayList<String> lst = new ArrayList<>();
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            new File(path).getParentFile().mkdirs();
            try {
                new File(path).createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (!file.exists()) {
            try {
                new File(path).createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                lst.add(sc.nextLine());
            }
            sc.close();
            return lst;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return lst;
    }

    /**
     * Updates list of tasks in the storage file
     * @param lst latest list of tasks to be written into storage
     */
    public void update(ArrayList<Task> lst) throws IOException{
        File file = new File(path);
        FileWriter fw = new FileWriter(this.path);
        for (int i = 0; i < lst.size(); i++) {
            fw.write(lst.get(i).toString() + "\n");
        }
        fw.close();
    }
}
