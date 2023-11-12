package duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;
import duke.util.Ui;

/**
 * Stores the data in the hard disk.
 * @author Jicson Toh
 */
public class Storage {
    private static final Ui ui = new Ui();
    private File data;

    /**
     * Creates Storage object.
     * @param filePath file path of data.
     */
    public Storage(String filePath) {
        try {
            data = new File(filePath);
            if (!data.getParentFile().exists()) {
                data.getParentFile().mkdirs();
            }
            if (!data.exists()) {
                data.createNewFile();
            }
            assert data.exists();
        } catch (IOException e) {
            ui.showError();
        }
    }

    public File loadData() {
        return this.data;
    }

    /**
     * Updates the data from the array list to the file.
     * @param taskList task list to be stored in.
     */
    public void saveData(TaskList taskList) {
        try {
            ArrayList<Task> list = taskList.getList();
            FileWriter writer = new FileWriter(data, false);
            for (Task task : list) {
                String toAdd = "";
                String line = task.toString();
                char type = line.charAt(1);
                String isDone = task.getDone() ? "1" : "0";
                String action = task.getAction();
                String date = task.getDate();
                toAdd += type + " | " + isDone + " | " + action;
                if (type != 'T') {
                    toAdd += " | " + date;
                }
                writer.write(toAdd + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.showError();
        }
    }

}
