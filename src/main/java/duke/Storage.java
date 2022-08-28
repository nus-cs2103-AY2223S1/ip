package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * Storage class manages the saving of data and loading of saved data
 */
public class Storage {
    private String saveFilePath;
    private String saveDirectoryPath;

    /**
     * Class Constructor for storage using directory and file paths
     * @param saveDirectoryPath path to directory containing file
     *                          where data is saved
     * @param saveFilePath path to file where data is saved
     */
    public Storage(String saveDirectoryPath, String saveFilePath) {
        this.saveDirectoryPath = saveDirectoryPath;
        this.saveFilePath = saveFilePath;
    }

    /**
     * Saves given String to specified file location
     * @param textToSave content to be saved
     * @throws IOException error if failed to write/ save data
     */
    public void writeToFile(String textToSave) throws IOException {
        FileWriter fw = new FileWriter(this.saveFilePath);
        fw.write(textToSave);
        fw.close();
    }

    /**
     * Checks if the specified file exists, otherwise creates the directory and file accordingly
     * @param tasklist list of tasks to be save
     * @throws IOException error if specified file cannot be read
     * @throws FileNotFoundException error if specified file cannot be found
     */
    public void checkExistsOrCreateNewFile(TaskList tasklist) throws IOException, FileNotFoundException {
        File f = new File(this.saveFilePath);
        if (f.exists()) {
            readFile(f, tasklist);
        } else {
            File dir = new File(this.saveDirectoryPath);
            dir.mkdir();
            f.createNewFile();
        }
    }

    /**
     * Reads the contents of a specified file and populates the task list accordingly
     * @param f file from which contents are to be read
     * @param tasklist Taskslist to which tasks read are to be appended
     * @throws FileNotFoundException error if specified file is not found
     */
    private void readFile(File f, TaskList tasklist) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        String tempLine = "";
        while (s.hasNext()) {
            tempLine = s.nextLine();
            String[] tempWords = tempLine.split(" , ");
            boolean isCompleted = tempWords[1].contains("true");
            if (tempWords[0].equals("T")) {
                tasklist.appendToDoFromFile(tempWords[2], isCompleted);
            } else if (tempWords[0].equals("E")) {
                tasklist.appendEventFromFile(tempWords[2], tempWords[3], isCompleted);
            } else if (tempWords[0].equals("D")) {
                tasklist.appendDeadlineFromFile(tempWords[2], tempWords[3], isCompleted);
            }
        }
    }
}
