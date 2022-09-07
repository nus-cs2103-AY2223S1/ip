package doke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * a class to represents the storage.
 */
public class Storage {
    protected static final File DOKE_FILE = new File(Doke.DOKE_FILE_PATH);

    /**
     * Updates the storage file using the arrayList as reference.
     *
     * @param arrayList containing the task to be written to the file.
     */
    public void updateFile(ArrayList<Task> arrayList, Ui ui) {
        try {
            FileWriter writer = new FileWriter(Doke.DOKE_FILE_PATH);
            writer.write("");
            for (int i = 0; i < arrayList.size(); i++) {
                writeToFile(createWordForFile(arrayList.get(i)));
            }
            writer.close();

        } catch (IOException e) {
            ui.printOut("Sorry, something went wrong");
        }
    }

    /**
     * Writes to the file a task which has been added.
     *
     * @param string String representation of the task
     * @throws IOException In case of failure of writing to the file
     */
    public void writeToFile(String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(DOKE_FILE, true));
        writer.append(string + "\n");
        writer.close();
    }

    /**
     * Returns a String representation of the Task suitable for the storage file.
     *
     * @param task task to be represented.
     * @return a string representation of the task which can be written to the storage file.
     */
    public static String createWordForFile(Task task) {
        String time = task instanceof ToDo ? "" : " | " + task.getTime();
        String action = task.getType() + " | " + task.getStatus() + " | "
                + task.getDesc() + time;
        return action;
    }
}
