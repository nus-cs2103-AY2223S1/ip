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
    public static final File dokeFile = new File(Doke.dokeFilePath);

    /**
     * Updates the storage file using the arrayList as reference.
     *
     * @param arrayList containing the task to be written to the file.
     */
    public void updateFile(ArrayList<Task> arrayList) {
        try {
            FileWriter writer = new FileWriter(Doke.dokeFilePath);
            writer.write("");
            for(int i = 0; i < arrayList.size(); i++) {
                writeToFile(createWordForFile(arrayList.get(i)));
            }
            writer.close();

        } catch (IOException e) {}
    }

    /**
     * Writes to the file a task which has been added.
     *
     * @param string String representation of the task
     * @throws IOException In case of failure of writing to the file
     */
    public void writeToFile(String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(dokeFile, true));
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
        String action = task.getType() + " | " + task.getStatus() + " | " +
                task.getDesc() + time;
        return action;
    }
}
