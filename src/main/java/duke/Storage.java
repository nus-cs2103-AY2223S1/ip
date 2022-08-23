package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Storage for the Duke chatbot, storing and restoring files into/from a save file.
 *
 * @author Aaron Tan
 */
public class Storage {

    private static final File FILE_PATH = new File("src/main/java/data/duke.txt");

    /**
     * Loads data from a save file.
     *
     * @return A TaskList with data loaded from a savefile.
     */
    protected static TaskList readData() {
        try {
            Scanner fileScanner = new Scanner(FILE_PATH);
            TaskList tasks = new TaskList();
            while (fileScanner.hasNextLine()) {
                String[] info = fileScanner.nextLine().split(" \\| ");
                String type = info[0];
                boolean isDone = info[1].equals("1") ? true : false;
                String description = info[2];
                Task task;
                switch (type) {
                case "T":
                    task = new Todo(description, isDone);
                    break;
                case "D":
                    task = new Deadline(description, isDone, info[3]);
                    break;
                case "E":
                    task = new Event(description, isDone, info[3]);
                    break;
                default:
                    throw new DukeException("invalid task type, try T, D, E");
                }
                tasks.add(task);
            }
            fileScanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("data cannot be found");
        } catch (DukeException e) {
            System.out.println("data cannot be read");
        }
        return null;
    }

    /**
     * Saves data from given TaskList into save file.
     *
     * @param tasks TaskList to be saved.
     */
    protected void saveData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String toSave = task.saveString();
                fw.write(toSave);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("data cannot be saved");
        }
    }
}
