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

    private File f;
    private static final String PATH = "src/main/data/userTasks.txt";

    /**
     * Initialises the Storage class with a specified PATH constant
     */
    public Storage() {
        f = new File(PATH);
    }

    /**
     * Retrieves the tasks read from the userTasks.txt file
     *
     * @return an ArrayList of Strings containing the tasks from userTasks.txt
     * @throws DukeException
     */
    public ArrayList<String> retrieveTasks() throws DukeException {
        ArrayList<String> strArr = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String sentence = sc.nextLine().trim();
                strArr.add(sentence);
            }
         } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return strArr;
    }

    /**
     * Writes the tasks stored in a specified ArrayList to the userTasks.txt file
     *
     * @param tasks an ArrayList of tasks to be stored in the local file
     */
    public void writeToFile(ArrayList<? extends Task> tasks) {
        assert(tasks != null);
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            String s = tasks.get(i).toStringForFile();
            str += s + System.lineSeparator();
        }
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