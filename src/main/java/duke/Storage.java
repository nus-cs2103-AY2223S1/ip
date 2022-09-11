package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Storage {
    private final String filePath;

    /**
     * A constructor for Storage.
     *
     * @param filePath The filepath in which the saved data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Write the lines from the list into the save file.
     *
     * @param dataArrayList ArrayList containing the data to be written into the save file.
     */
    public void save(ArrayList<String> dataArrayList) {
        try {
            new File(filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);
            for (String data : dataArrayList) {
                fw.write(String.format("%s%n", data));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("\n\tOOPS!!! I'm sorry, but I'm unable \nto write to file due to: " + e.getMessage());
        }
    }

    /**
     * Returns the list read from the save file.
     *
     * @return ArrayList containing the data read from the save file.
     * @throws FileNotFoundException If the save file does not exist.
     */
    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        sc.close();

        return data;
    }
}
