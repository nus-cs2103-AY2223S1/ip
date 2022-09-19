package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Storage {

    public final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all the saved tasks from file
     * @return ArrayList of tasks loaded
     * @throws FileNotFoundException if file cannot be found
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        try {
            ArrayList<Task> ls = new ArrayList<>();
            Ui dummyUi = new Ui();
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                if (input.equals("")) { // for empty spacing in nextLine
                    continue;
                }
                String commandType = Parser.getCommandType(input);
                Parser.process(input, commandType, ls, dummyUi);
            }
            return ls;
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("File cannot be found!");
        }
    }

    /**
     * Responsible for saving the user's input into the file
     * @param text task to save
     * @throws IOException if the user's input cannot be saved
     */
    public void saveToFile(String text) throws IOException {
        try {
            Files.write(Paths.get(filePath), ("\n" + text).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IOException("Error occurred while appending text to file.");
        }
    }

}
