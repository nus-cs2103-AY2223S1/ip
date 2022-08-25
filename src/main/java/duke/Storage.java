package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
            Task curr;
            boolean isDone;
            LocalDate localDate;
            String description;
            while (line != null) {
                line = line.replace("\n", "");
                if (line.substring(4, 5).equals("0")) {
                    isDone = false;
                } else {
                    isDone = true;
                }
                switch (line.substring(0, 1)) {
                case "T":
                    description = line.split("\\|", 3)[2];
                    description = description.substring(1);
                    curr = new Todo(description);
                    curr.setDone(isDone);
                    taskList.addTask(curr);
                    break;
                case "E":
                    description = line.split("\\|", 4)[2];
                    description = description.substring(1, description.length()-1);
                    localDate = LocalDate.parse(line.split("\\|", 4)[3].substring(1));
                    curr = new Event(description, localDate);
                    curr.setDone(isDone);
                    taskList.addTask(curr);
                    break;
                case "D":
                    description = line.split("\\|", 4)[2];
                    description = description.substring(1, description.length()-1);
                    localDate = LocalDate.parse(line.split("\\|", 4)[3].substring(1));
                    curr = new Deadline(description, localDate);
                    curr.setDone(isDone);
                    taskList.addTask(curr);
                    break;
                default:
                }
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
