package Duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    static String filePath;
    BufferedReader bR;

    public Storage(String filePath) {
        Storage.filePath = filePath;
        if (!Files.exists(Paths.get(filePath))) {
            try {
                Files.createFile(Paths.get(filePath));
            } catch (IOException e) {
                Ui.printErrorMessage(e.toString());
            }
        }
    }

    public ArrayList<Task> load() {
        try {
            bR = new BufferedReader(new FileReader(filePath));
            ArrayList<Task> tasksToDo = new ArrayList<>();
            String currLine = bR.readLine();
            while (currLine != null) {
                String[] details = currLine.split("\\|");
                boolean done = !Objects.equals(details[1], "0");
                String type = details[0];
                String date[];
                switch (type) {
                    case "T":
                        tasksToDo.add(new Todo(details[2], done));
                        break;
                    case "D":
                        date = details[3].split("T");
                        tasksToDo.add(new Deadline(details[2], done, date[0] + " " + date[1]));
                        break;
                    case "E":
                        date = details[3].split("T");
                        tasksToDo.add(new Event(details[2], done, date[0] + " " + date[1]));
                        break;
                    default:
                        break;
                }
                currLine = bR.readLine();
            }
            return tasksToDo;
        } catch (IOException e) {
            Ui.printErrorMessage(e.toString());
        }
        return null;
    }

    public static boolean updateFile(Task task) {
        try {
            String taskStr = task.getType() == 'T' ?
                    ('T' + "|" + task.getDone() + "|" + task.getTask())
                    : (task.getType() + "|" + task.getDone() + "|" + task.getTask() + "|" + task.getOriginalDetail());
            Files.write(Paths.get(filePath), (taskStr + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            Ui.printErrorMessage(e.toString());
            return false;
        }
    }

    public static boolean rewriteEntireFile(ArrayList<Task> tasks) {
        try {
            BufferedWriter bW = Files.newBufferedWriter(Paths.get(filePath));
            bW.write("");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                updateFile(task);
            }
            return true;
        } catch (IOException e) {
            Ui.printErrorMessage(e.toString());
            return false;
        }
    }
}
