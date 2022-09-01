package Duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The class deals with loading tasks from the file and saving tasks in the file
 * @author LimWeiJun
 */
public class Storage {
    static String filePath;
    BufferedReader bR;
    File dukeFile;
    Ui ui;

    /**
     * The method takes in two parameters
     * @param pathName of type String
     * @param fileName of type String
     */
    public Storage(Ui ui, String pathName, String fileName) {
        this.ui = ui;
        Path file = Paths.get(pathName);
        File curr = new File(String.valueOf(pathName), fileName);
        filePath = pathName + "/" + fileName;
        if (!Files.exists(Paths.get(pathName + fileName))) {
            curr.getParentFile().mkdir();
            try {
                curr.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.dukeFile = curr;
    }

    /**
     * The method will load
     * @return returns an object of type ArrayList
     */
    public ArrayList<Task> load() {
        try {
            bR = new BufferedReader(new FileReader(dukeFile));
            ArrayList<Task> tasksToDo = new ArrayList<>();
            String currLine = bR.readLine();
            while (currLine != null) {
                String[] details = currLine.split("\\|");
                boolean done = !Objects.equals(details[1], "0");
                String type = details[0];
                String[] date;
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
            ui.printErrorMessage(e.toString());
        }
        return null;
    }

    /**
     * The method is a static and takes in a parameter and returns a boolean
     * @param task The input to be received
     * @return returns a boolean
     */
    public boolean updateFile(Task task) {
        try {
            String taskStr = task.getType() == 'T' ?
                    ('T' + "|" + task.getDone() + "|" + task.getTask())
                    : (task.getType() + "|" + task.getDone() + "|" + task.getTask() + "|" + task.getOriginalDetail());
            Files.write(Paths.get(filePath), (taskStr + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            ui.printErrorMessage(e.toString());
            return false;
        }
    }

    /**
     * The method is a static and takes in a parameter
     * @param tasks The input to be received
     * @return returns a boolean
     */
    public boolean rewriteEntireFile(ArrayList<Task> tasks) {
        try {
            BufferedWriter bW = Files.newBufferedWriter(Paths.get(filePath));
            bW.write("");
            for (Task task : tasks) {
                updateFile(task);
            }
            return true;
        } catch (IOException e) {
            ui.printErrorMessage(e.toString());
            return false;
        }
    }
}
