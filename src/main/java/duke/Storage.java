package duke;

import java.io.*;
import java.util.ArrayList;

class Storage {
    private String filePath;

    Storage(String arg) {
        this.filePath = arg;
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
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Ui.output("Fail to write file.");
            return -1;
        }
        return 0;
    }
}
