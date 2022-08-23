package doke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    public static final File dokeFile = new File(Doke.dokeFilePath);

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

    public void writeToFile(String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(dokeFile, true));
        writer.append(string + "\n");
        writer.close();
    }

    public static String createWordForFile(Task task) {
        String time = task instanceof ToDo ? "" : " | " + task.getTime();
        String action = task.getType() + " | " + task.getStatus() + " | " +
                task.getDesc() + time;
        return action;
    }


}
