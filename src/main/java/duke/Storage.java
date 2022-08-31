package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final File file;

    /**
     * Creates a new storage instance from the given pathname string.
     * @param pathname A pathname string.
     */
    public Storage(String pathname) {
        this.file = new File(pathname);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException exception) {
                System.out.println("Error creating new data file");
            }
        }
    }

    /**
     * Reads and return a string representation of the stored tasks.
     * @return A string representing the stored tasks in the file.
     */
    public String read() {
        try {
            Scanner scanner = new Scanner(this.file);
            StringBuilder contents = new StringBuilder();

            if (!scanner.hasNextLine()) {
                return null;
            }
            contents.append(scanner.nextLine());

            while (scanner.hasNextLine()) {
                contents.append(System.lineSeparator());
                contents.append(scanner.nextLine());
            }

            scanner.close();
            return contents.toString();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File is not found when attempting to read data file from storage");
        }
    }

    /**
     * Writes the tasks to the Storage file.
     * @param contents A string representing the tasks to be stored in the Storage file.
     */
    public void write(String contents) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(contents);
            fileWriter.close();
        } catch (IOException exception) {
            throw new RuntimeException("An I/O exception occurred when attempting to write to data file");
        }
    }






}
