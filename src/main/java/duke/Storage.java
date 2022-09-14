package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class handles the loading and saving of data from the user's machine.
 *
 * @author Eugene Tan
 */
public class Storage {
    private String filePath;
    private File dataFile;

    /**
     * Constructor class for storage.
     *
     * @param filePath The file path to find the necessary file.
     */
    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            File folder = new File(filePath);
            folder.mkdir();
            this.dataFile = new File(filePath + "/bob.txt");
            if (!this.dataFile.exists()) {
                this.dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong in creating the file");
        }
    }

    /**
     * Returns ArrayList containing lines read from the file.
     *
     * @return List contating the lines read from the file.
     * @throws FileNotFoundException if the file cannot be found.
     */
    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> loadedData = new ArrayList<>();
        File file = new File(filePath + "/bob.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            loadedData.add(sc.nextLine());
        }

        sc.close();

        return loadedData;
    }

    /**
     * Writes and saves lines in a list to a file.
     *
     * @param data List containing lines to be written to the file.
     */
    public void save(ArrayList<String> data) {
        try {
            FileWriter file = new FileWriter(filePath + "/bob.txt");

            for (int i = 0; i < data.size(); i++) {
                file.write(String.format("%s%n", data.get(i)));
            }

            file.close();
        } catch (IOException e) {
            System.out.println("Error encountered : " + e.getMessage());
        }
    }
}

