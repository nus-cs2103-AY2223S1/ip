package doke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected static final File DOKE_FILE = new File(Doke.DOKE_FILE_PATH);

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

    public void writeToFile(String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(DOKE_FILE, true));
        writer.append(string + "\n");
        writer.close();
    }

    public static String createWordForFile(Task task) {
        String time = task instanceof ToDo ? "" : " | " + task.getTime();
        String action = task.getType() + " | " + task.getStatus() + " | "
                + task.getDesc() + time;
        return action;
    }


}
