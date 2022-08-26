package pixel.util;

// Storage: deals with loading tasks from the file and saving tasks in the file
import pixel.Pixel;
import pixel.task.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Handles the storage functions
 * - Stores, modifies and deletes new tasks on external .txt file
 * - Deletes tasks from ArrayList of Tasks (in Pixel.java)
 * - (future) handles all operations on Arraylist of Tasks
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for a new Storage object
     *
     * @param filePath absolute path of the file where the tasks will be saved
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
   /* During the write operation, the characters are written to the internal buffer instead of the disk.
   Once the buffer is filled or the writer is closed, the whole characters in the buffer are
   written to the disk.
   Hence, the number of communication to the disk is reduced.
   */

    /**
     * Clears the output file
     *
     * @throws IOException
     */
    public void resetFile() throws IOException {
        new FileWriter(this.filePath, false).close();
    }

    /**
     * Removes a task of a particular index of the list from the ArrayList
     * and updates the external file
     *
     * @param userInput input from the user, starting with "delete ..."
     * @throws IOException
     */
    public void deleteEntry(String userInput) throws IOException {

        Task tempRecord;
        // truncate the front part
        String temp = userInput.substring(7);
        // System.out.println(temp);
        int indexToDelete = Character.getNumericValue(temp.charAt(0));
        // System.out.println(indexToChange);
        if ((indexToDelete > 0) && (indexToDelete < 100)) {
            tempRecord = Pixel.inputTasks.get(indexToDelete - 1);
            int originalInputListSize = Pixel.inputTasks.size();

            System.out.println("Noted. I've removed this task:");
            System.out.println(tempRecord);
            System.out.println(originalInputListSize + " input task size");

            // shift everything forward by 1, starting at the element to be removed (which is replaced by next element)
            for (int i = (indexToDelete - 1); i < originalInputListSize; i++) {
                // move everything up by 1
                if (i == (originalInputListSize - 1)) {
                    // System.out.println(i + " remove");
                    Pixel.inputTasks.remove(i);
                } else {
                    // System.out.println(i + " replace");
                    Pixel.inputTasks.set(i, Pixel.inputTasks.get(i + 1));
                }
            }

            resetFile();
            for (Task task : Pixel.inputTasks) {
                appendToFile(task);
            }

            Pixel.count -= 1;
            System.out.println("Now you have " + Pixel.count + " tasks in the list.");
        }

    }

    /**
     * Appends a new task to the external file
     *
     * @param task new task to be appended
     * @throws IOException
     */
    public void appendToFile(Task task) throws IOException {
        String textToAdd = task.formatTaskBeforeSave();
        Writer bufferedFileWriter = new BufferedWriter(new FileWriter(this.filePath, true)); // FileWriter(String fileName, boolean append)
        bufferedFileWriter.append(textToAdd).append("\n");
        bufferedFileWriter.close();
    }
}