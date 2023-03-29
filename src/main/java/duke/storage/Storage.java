package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Performs read and write operations to the userTasks file in the user's
 * local storage
 */
public class Storage {

    private static final String HOME_DIRECTORY = System.getProperty("user.dir");
    private static final String TARGET_DIRECTORY = HOME_DIRECTORY + "/src/main/data";
    private static final String PATH = TARGET_DIRECTORY + "/userTasks.txt";

    private final File f;
    private ArrayList<String> stringArray;

    /**
     * Initialises the Storage class with a specified PATH constant
     *
     * @throws DukeException if the file cannot be created
     */
    public Storage() throws DukeException {
        f = initialize();
    }

    /**
     * Initialises the user tasks file for the storage directory
     *
     * @return A file containing the user tasks
     * @throws DukeException if the file cannot be created
     */
    public File initialize() throws DukeException {
        try {

            File srcDirectory = new File(HOME_DIRECTORY + "/src");
            if (!srcDirectory.exists()) {
                srcDirectory.mkdir();
            }

            File mainDirectory = new File(HOME_DIRECTORY + "/src/main");
            if (!mainDirectory.exists()) {
                mainDirectory.mkdir();
            }

            File parentDirectory = new File(TARGET_DIRECTORY);

            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }

            File userTasks = new File(PATH);

            if (!userTasks.exists()) {
                userTasks.createNewFile();
            }

            return userTasks;

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Retrieves the tasks read from the userTasks.txt file
     *
     * @return an ArrayList of Strings containing the tasks from userTasks.txt
     * @throws DukeException if the file to read from is not found
     */
    public ArrayList<String> retrieveTasks() throws DukeException {
        initialiseStringArray();
        addElementsToArray();
        return stringArray;
    }

    /**
     * Initialises the stringArray instance as an empty ArrayList of String objects
     */
    private void initialiseStringArray() {
        stringArray = new ArrayList<>();
    }

    /**
     * Adds the elements read from the file to the array
     *
     * @throws DukeException if the file to read from is not found
     */
    private void addElementsToArray() throws DukeException {
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String sentence = sc.nextLine().trim();
                stringArray.add(sentence);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Writes the tasks stored in a specified ArrayList to the userTasks.txt file
     *
     * @param tasks an ArrayList of tasks to be stored in the local file
     */
    public void writeToFile(ArrayList<? extends Task> tasks) {
        assert(tasks != null);
        String str = getStringFromTasks(tasks);
        writeStringToPath(str);
    }

    /**
     * Gets the string representation of the tasks to be written to the file
     *
     * @param tasks an ArrayList of tasks to be stored in the local file
     * @return the string representation of the tasks to be written to the file
     */
    private String getStringFromTasks(ArrayList<? extends Task> tasks) {
        String str = "";
        for (Task task : tasks) {
            String s = task.toStringForFile();
            str += s + System.lineSeparator();
        }
        return str;
    }

    /**
     * Writes the string to a file of a specified path
     *
     * @param str the string representation of the tasks to be written to the file
     */
    private void writeStringToPath(String str) {
        try (BufferedWriter bf = Files.newBufferedWriter(Path.of(PATH),
                StandardOpenOption.TRUNCATE_EXISTING)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(PATH, true));
            fw.append(str);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
