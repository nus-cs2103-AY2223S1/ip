package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String dataFileName;

    public Storage(String filePath) {
        this.dataFileName = filePath;
    }

    public void writeToFile(TaskList taskL) throws IOException {
        //structure: command|1 (1 for mark, 0 for unmark)
        ArrayList<Task> arr = taskL.getTasks();
        FileWriter fw = new FileWriter(this.dataFileName);
        for (int i = 0; i < arr.size(); i++) {
            fw.write(arr.get(i).getData() + "\n");
        }
        fw.close();
    }

    public String loadFromFile(TaskList taskL) {
        try {

            File f = new File(dataFileName);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                taskL.add(new Task(line));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return "";
    }
}
