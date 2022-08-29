package duke;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that handles loading and saving of tasks for Duke Bot.
 */
public class Storage {
    private FileWriter output;
    private String previousText;

    /**
     * Class constructor for Storage.
     * 
     * Calling the constructor will:
     * 1. Create a data folder if it doesn't already exist.
     * 2. Load text at data/duke.txt if it exists.
     * 3. Erases file at data/duke.txt if it exists to write next save file.
     */
    public Storage() {
        File file = new File("data");
        if (!file.exists()) {
            // make data directory if it doesnt exist
            file.mkdir();
        } else if (new File("data/duke.txt").exists()) {
            // else if save file exists, load it into memory
            try {
                previousText = Files.readString(Path.of("data/duke.txt"));
            } catch (java.io.IOException e) {
                System.out.println("Unable to read storage file: " + e);
            }
        }
        try {
            output = new FileWriter("data/duke.txt");
        } catch (java.io.IOException e) {
            System.out.println("Unable to write storage file: " + e);
        }
    }

    /**
     * Returns saved text from previous instance of Duke Bot.
     * 
     * @return Saved text from previous instance of Duke Bot.
     */
    public String getPreviousText() {
        return previousText;
    }

    /**
     * Writes a string to save file of Duke Bot.
     * 
     * @param s String to be written to save file.
     * @param nextLine Boolean, if true newline character will be written after string is written.
     */
    public void writeText(String s, boolean nextLine) {
        try {
            output.write(s);
            if (nextLine) {
                output.write("\n");
            }
        } catch (java.io.IOException e) {
            System.out.println("Failed to write text to save file: " + e);
        }
    }

    /**
     * Closes FileWriter instance for writing save file.
     */
    public void closeWriter() {
        try {
            output.close();
        } catch (java.io.IOException e) {
            System.out.println("Failed to close save file: " + e);
        }
    }
}
