package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Storage {
    private String filePath;

    Storage(String arg) {
        this.filePath = arg;
    }

    void readResult(TaskList taskList) {
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
            Ui.output("Unable to load existing record. Generating a new one.");
        }
    }

    int writeResult(TaskList taskList) {
        int endIndex = filePath.lastIndexOf("/");
        String dirPath = filePath.substring(0, endIndex);
        File dest = new File(dirPath);

        // create dir if not exist
        boolean isPathExist = dest.exists();
        if (!isPathExist) {
            dest.mkdir();
        }

        File file = new File(filePath);
        ArrayList<Task> arrayList = taskList.taskArrayList;
        try {
            FileWriter fw = new FileWriter(file);

            for (Task t : arrayList) {
                fw.write(t.toStorageString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Ui.output("Fail to write file.");
            return -1;
        }
        return 0;
    }
}
