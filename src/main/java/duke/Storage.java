package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class to read and write file
 */
class Storage {
    private String filePath;

    Storage(String arg) {
        this.filePath = arg;
    }

    /**
     * Reads stored result into the task list.
     *
     * @param taskList the target destination to store result
     */
    protected void readResult(TaskList taskList) {
        File file = new File(filePath);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                line = line.replace("\n", "");
                taskList.readPreCreatedTask(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            Ui.formatPrint("Unable to load existing record. Generating a new one.");
        }
    }

    /**
     * Writes the task list result to the file.
     *
     * @param taskList the task list that stores the information to be written
     * @return 0 if successfully written in else 1
     */
    protected int writeResult(TaskList taskList) {
        int endIndex = filePath.lastIndexOf("/");
        String dirPath = filePath.substring(0, endIndex);
        File dest = new File(dirPath);

        // create dir if not exist
        boolean isPathExist = dest.exists();
        if (!isPathExist) {
            dest.mkdir();
        }

        File file = new File(filePath);
        ArrayList<Task> arrayList = taskList.getTaskArrayList();
        try {
            FileWriter fw = new FileWriter(file);

            for (Task t : arrayList) {
                fw.write(t.toStorageString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Ui.formatPrint("Fail to write file.");
            return -1;
        }
        return 0;
    }
}
