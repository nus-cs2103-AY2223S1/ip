package duke.storage;

import duke.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Storage {
    private static String home = System.getProperty("user.home");
    private static final String FILE_PATH = home + "/" + "data/Duke.txt";

    private String filePath;

    public Storage(String filePath) {
        this.filePath = home + "/" + filePath;
        checkDirectory();
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String res = taskList.toStorage();
            fw.write(res);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> load() {
        String line = "";
        List<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                line = sc.nextLine();
                if (!line.equals("")) {
                    String[] str = line.split(" \\| ");
                    Task t = null;
                    switch (str[0]) {
                        case "T":
                            t = new ToDo(str[2]);
                            break;
                        case "D":
                            t = new Deadline(str[2], str[3]);
                            break;
                        case "E":
                            t = new Event(str[2], str[3]);
                            break;
                    }
                    tasks.add(t);
                    if (parseInt(str[1]) == 1) {
                        t.mark();
                    }
                    Task.incrementNumOfTasks();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void checkDirectory() {
        // Create directory if does not exist
        File dir = new File(filePath.split("/")[0]);
        if (!dir.exists()) {
            Boolean directoryCreated = dir.mkdirs();
        }

        // Create file if does not exist
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                Boolean fileCreated = f.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

//        FileWriter fw = new FileWriter(filePath, true);
//        if (taskType.equals(TODO)) {
//            fw.write("T | 0 | " + userInput + "\n");
//        } else if (taskType.equals(DEADLINE)) {
//            String str = userInput.replace("/by", "|");
//            fw.write("D | 0 | " + str + "\n");
//        } else {
//            String str = userInput.replace("/at", "|");
//            fw.write("E | 0 | " + str + "\n");
//        }
//        fw.close();
    }
}
