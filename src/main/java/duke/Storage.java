package duke;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private FileWriter output;
    private String previousText;

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

    public String getPreviousText() {
        return previousText;
    }

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

    public void closeWriter() {
        try {
            output.close();
        } catch (java.io.IOException e) {
            System.out.println("Failed to close save file: " + e);
        }
    }
}
