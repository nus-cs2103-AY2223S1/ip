package duke.util;

//import io
import duke.Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import util
import java.util.Scanner;

/**
 * Represents a Storage to store task input by the user.
 */
public class Storage {
    public static final String STORAGE_UPDATE_MESSAGE = "Storage: " + Duke.FILE_NAME +" updated";
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
    public TaskList setUp() throws IOException {
        if (isFilePresent()) {
            return new TaskList(load());
        }
        dukeFile.createNewFile();
        return new TaskList();
    }

    /**
     * Updates the file with the new contents in TaskList.
     *
     * @param tasks task list to be written into the file.
     */
    public String update(TaskList tasks) {
        try {
            fileWriter = new FileWriter(dukeFile);
            fileWriter.write(tasks.toString());
            fileWriter.close();
            return STORAGE_UPDATE_MESSAGE;
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    /**
     * Loads the file content into a String Representation.
     *
     * @return String representation of the content in the file.
     */
    private String load() {
        try {
            String data = "";
            scanner = new Scanner(dukeFile);
            while (scanner.hasNextLine()) {
                data += scanner.nextLine() + '\n';
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }
    }
}
