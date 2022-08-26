package main.java.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Class for storing and loading tasks from a file.
 */
public class Storage {
    /**
     * Saves the contents of a TaskList into the a file.
     * @param list - the list of tasks to load into
     */
    public static void save(TaskList list) {
        try {
            File file = new File("./data/duke.txt");
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
            file.createNewFile();
            FileWriter writer = new FileWriter("./data/duke.txt", false);
            writer.write(list.saveData());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the contents of a TaskList into the program.
     * @param filePath - the name of the file to load from
     * @return the list of tasks loaded from the file
     */
    public static TaskList load(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
            file.createNewFile();

            TaskList result = new TaskList();
            BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] raw = line.split(" \\| ");
                TaskList.Task curr = null;
                switch (raw[0]) {
                case "T":
                    curr = result.addTask(raw[2]);
                    break;
                case "D":
                    curr = result.addDeadline(raw[2], raw[3]);
                    break;
                case "E":
                    curr = result.addEvent(raw[2], raw[3]);
                    break;
                }
                if (raw[1].equals("true") && curr != null) {
                    curr.mark();
                }
                line = reader.readLine();
            }
            result.printData();
            return result;
        } catch (IOException e) {
            System.out.println("No save file found.");
            return new TaskList();
        }
    }
}
