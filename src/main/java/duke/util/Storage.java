package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

import iohelper.IoHelper;

/**
 * Represents a Storage to store task input by the user.
 */
public class Storage {
    private final IoHelper ioHelper = new IoHelper();
    private File dukeFile;
    private FileWriter fileWriter;
    private Scanner scanner;

    /**
     * Constructs Storage object with File to be read and written to.
     *
     * @param fileName Name of the file to read and write to.
     */
    public Storage(String fileName) {
        dukeFile = new File(fileName);
    }

    /**
     * Shows whether file is present or not.
     * @return True if file is present else false.
     */
    private boolean isFilePresent() {
        return dukeFile.isFile();
    }

    /**
     * Sets up the storage object when duke is booted up.
     * if file not present, create file.
     * if file present, load the content in the file.
     *
     * @return TaskList with either task stored in the file or empty TaskList
     */
    public TaskList setUp() {
        if (isFilePresent()) {
            return new TaskList(load());
        }
        try {
            dukeFile.createNewFile();
        } catch (IOException e) {
            ioHelper.print(e.getMessage());
        } finally {
            return new TaskList();
        }
    }

    /**
     * Updates the file with the new contents in TaskList.
     *
     * @param obj Object to be written into the file.
     */
    public void update(Object obj) {
        try {
            fileWriter = new FileWriter(dukeFile);
            fileWriter.write(obj.toString());
            ioHelper.print("Storage: duke.txt updated");
            fileWriter.close();
        } catch (IOException e) {
            ioHelper.print(e.getMessage());
        }
    }

    /**
     * Loads the file content into a String Representation.
     *
     * @return String representation of the content in the file.
     */
    private String load() {
        String data = "";
        try {
            scanner = new Scanner(dukeFile);
            while (scanner.hasNextLine()) {
                data += scanner.nextLine() + '\n';
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ioHelper.print(e.getMessage());
        }
        return data;
    }
}
