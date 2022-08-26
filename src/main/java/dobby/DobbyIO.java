package dobby;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * DobbyIO is a class that handles the loading and saving of tasks to and from the file.
 */
public class DobbyIO {
    /**
     * Saves the list of tasks to a file.
     *
     * @param dl list of tasks to be saved.
     * @throws IOException file cannot be saved
     */
    public static void save(DobbyList dl) throws IOException {

        File newFile = new File("./src/main/dobbyList.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile.getAbsoluteFile()));
        bw.write(dl.toPrint());
        bw.close();
    }

    /**
     * Loads tasks from the file.
     *
     * @param dl list of tasks to load to
     * @param filePath path of file to load from
     */
    public static void load(DobbyList dl, String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String task;
            while ((task = br.readLine()) != null) {
                if (!task.equals("")) {
                    dl.addTask(task);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}