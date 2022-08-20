package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage handles loading data from and saving data to files.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns list containing lines read from the file.
     *
     * @return List containing lines read from the file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> dataList = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);

        while (sc.hasNext()) {
            dataList.add(sc.nextLine());
        }
        sc.close();

        return dataList;
    }

    /**
     * Writes the lines in the given list to the file.
     *
     * @param dataList List containing lines to be written to the file.
     */
    public void save(ArrayList<String> dataList) {
        try {
            new File(filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);

            for (String data : dataList) {
                fw.write(String.format("%s%n", data));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
