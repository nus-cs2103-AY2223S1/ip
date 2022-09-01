package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file interaction between the <code>Parser</code> object
 * and the file stored on the hard disk of the user.
 */
public class Storage {

    private static String filePath;
    private File f;
    private ArrayList<String> tasksList;

    /**
     * Initialises a <code>Storage</code> object that initialises a <code>File</code>
     * object that will be used to read and write to. Initialises a <code>ArrayList</code>
     * to store the tasks that was contained in the text file.
     * @param filePath A <code>String</code> representing the file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(this.filePath);
        tasksList = new ArrayList<>();
    }

    /**
     * Reads the tasks from the file and loads them into an <code>ArrayList</code>
     * @return An <code>ArrayList</code> containing the tasks from the file.
     */
    public ArrayList<String> load() {
        try {
            if (!f.createNewFile()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String curr = sc.nextLine();
                    tasksList.add(curr);
                }
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasksList;
    }

    /**
     * Adds a task from the input string to the file.
     * @param textToAppend A <code>String</code> containing the description and status of a task.
     * @throws IOException If FILE_PATH is invalid.
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.write(System.lineSeparator());
        fw.close();
    }

    /**
     * Clears the entire file to an empty text file.
     * @throws FileNotFoundException If the file path is invalid.
     */
    public void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(f);
        writer.print("");
        writer.close();
    }
}
