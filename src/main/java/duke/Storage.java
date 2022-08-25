package main.java.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Storage {
    public static void save(TaskList list) {
        try {
            File file = new File("./data/duke.txt");
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
            file.createNewFile();
            FileWriter writer = new FileWriter("./data/duke.txt", false);
//                    System.out.println(this.saveData());
            writer.write(list.saveData());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
//                        System.out.println(line);

                String[] raw = line.split(" \\| ");
//                        System.out.println(String.format("%s, %s, %s", raw[0], raw[1], raw[2]));
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
            e.printStackTrace();
            return new TaskList();
        }
    }
}
