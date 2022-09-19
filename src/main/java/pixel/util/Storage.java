package pixel.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.util.ArrayList;

import pixel.Pixel;
import pixel.task.Task;

/**
 * Handles the storage functions
 * - Stores, modifies and deletes new tasks on external .txt file
 * - Deletes tasks from ArrayList of Tasks (in Pixel.java)
 * - (future) handles all operations on Arraylist of Tasks
 */
public class Storage {

    public static final ArrayList<Task> INPUT_TASKS = new ArrayList<>(100);

    /**
     * Constructor for a new Storage object
     */
    public Storage() {}

    /**
     * Finds if a tasks which description matches the query string exists
     *
     * @param userInput input from the user, starting with "find ..."
     * @return an ArrayList containing all the matching tasks
     * @throws IndexOutOfBoundsException when the user input contains less than 6 characters
     */
    public static ArrayList<Task> findEntry(String userInput) throws IndexOutOfBoundsException {
        ArrayList<Task> resultTasks = new ArrayList<>(100);
        // truncate the front part
        String queryString = userInput.substring(5).strip();

        for (Task task : Storage.INPUT_TASKS) {
            if (task.getDescription().contains(queryString)) {
                resultTasks.add(task);
            }
        }
        return resultTasks;
    }

    /* During the write operation, the characters are written to the internal buffer instead of the disk.
    Once the buffer is filled or the writer is closed, the whole characters in the buffer are
    written to the disk.
    Hence, the number of communication to the disk is reduced.
    */

    /* During the write operation, the characters are written to the internal buffer instead of the disk.
    Once the buffer is filled or the writer is closed, the whole characters in the buffer are
    written to the disk.

    Hence, the number of communication to the disk is reduced.
    */

    /**
     * Clears the output file
     *
     * @throws IOException when the filePath is incorrect
     */
    public static void resetFile(String filePath) throws IOException {
        new FileWriter(filePath, false).close();
    }

    /**
     * Removes a task of a particular index of the list from the ArrayList
     * and updates the external file
     *
     * @param userInput input from the user, starting with "delete ..."
     * @throws IOException when the filePath is invalid
     */
    public static String deleteEntry(String userInput, String filePath) throws IOException {
        String output = "";
        Task tempRecord;
        // truncate the front part
        String temp = userInput.substring(7).strip();
        int indexToDelete = Character.getNumericValue(temp.charAt(0));
        if ((indexToDelete > 0) && (indexToDelete < 100)) {
            tempRecord = Storage.INPUT_TASKS.get(indexToDelete - 1);
            int originalInputListSize = Storage.INPUT_TASKS.size();

            output += ("Noted. I've removed this task: \n"
                + tempRecord + "\n");

            // shift everything forward by 1, starting at the element to be removed (which is replaced by next element)
            for (int i = (indexToDelete - 1); i < originalInputListSize; i++) {
                // move everything up by 1
                if (i == (originalInputListSize - 1)) {
                    Storage.INPUT_TASKS.remove(i);
                    assert Storage.INPUT_TASKS.size() == (originalInputListSize - 1) : "Size of ArrayList of tasks did not decrease by 1 upon deletion";
                } else {
                    Storage.INPUT_TASKS.set(i, Storage.INPUT_TASKS.get(i + 1));
                }
            }

            resetFile(filePath);
            Storage.appendAllTasksToFile(filePath);

            Pixel.minusOneToTaskCount();
            output += ("Now you have " + Pixel.getTaskCount() + " tasks in the list.");
            return output;

        } else {
            return ("Choose an index between 1 and 100 to delete!");
        }
    }

    public static void appendAllTasksToFile(String filePath) throws IOException {
        for (Task task : Storage.INPUT_TASKS) {
            Storage.appendTaskToFile(task, filePath);
        }
    }

    /**
     * Appends a new task to the external file
     *
     * @param task new task to be appended
     * @throws IOException when the filePath is invalid
     */
    public static void appendTaskToFile(Task task, String filePath) throws IOException {
        String textToAdd = task.formatTaskBeforeSave();
        Writer bufferedFileWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedFileWriter.append(textToAdd).append("\n");
        bufferedFileWriter.close();
    }

}
