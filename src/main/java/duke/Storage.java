package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The class deals with loading tasks from the file and saving tasks in the file
 *
 * @author LimWeiJun
 */
public class Storage {
    static String filePath;
    BufferedReader bR;
    File dukeFile;
    MainWindow mainWindow;

    /**
     * A constructor that takes in three parameters
     *
     * @param pathName of type String
     * @param fileName of type String
     * @param mainWindow of type MainWindow
     */
    public Storage(String pathName, String fileName, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        Path file = Paths.get(pathName);
        File curr = new File(String.valueOf(pathName), fileName);
        filePath = pathName + "/" + fileName;
        if (!Files.exists(Paths.get(pathName + fileName))) {
            curr.getParentFile().mkdir();
            try {
                curr.createNewFile();
            } catch (IOException e) {
                this.mainWindow.printErrorMessage(e.toString());
            }
        }
        this.dukeFile = curr;
    }

    /**
     * Reads lines from duke file
     *
     * @return returns an object of type ArrayList
     */
    public ArrayList<Task> load() {
        try {
            bR = new BufferedReader(new FileReader(dukeFile));
            ArrayList<Task> tasksToDo = new ArrayList<>();
            String currLine = bR.readLine();
            return handleFileList(tasksToDo, currLine);
        } catch (IOException e) {
            mainWindow.printErrorMessage(e.toString());
        }
        return null;
    }

    /**
     * Adds a task to the existing duke file
     *
     * @param task The input to be received
     * @return returns a boolean
     */
    public boolean addFile(Task task) {
        try {
            String taskStr = task.getType() == 'T' ?
                    getTodoString(task)
                    : getEventDeadlineString(task);
            Files.write(Paths.get(filePath), (taskStr + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            mainWindow.printErrorMessage(e.toString());
            return false;
        }
    }

    /**
     * Rewrites the entire duke file with the existing tasks from array list
     *
     * @param tasks The input to be received
     * @return returns a boolean
     */
    public boolean rewriteEntireFile(ArrayList<Task> tasks) {
        try {
            BufferedWriter bW = Files.newBufferedWriter(Paths.get(filePath));
            bW.write("");
            for (Task task : tasks) {
                addFile(task);
            }
            return true;
        } catch (IOException e) {
            mainWindow.printErrorMessage(e.toString());
            return false;
        }
    }

    /**
     * Handles the different types of task to be added to the array list
     *
     * @param tasksToDo of type ArrayList
     * @return of type ArrayList
     */
    public ArrayList<Task> handleFileList(ArrayList<Task> tasksToDo, String currLine) throws IOException {
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
    }

    public String getTodoString(Task task) {
        return 'T' + "|" + task.getDone() + "|" + task.getTask();
    }

    public String getEventDeadlineString(Task task) {
        return task.getType() + "|" + task.getDone() + "|" + task.getTask() + "|" + task.getOriginalDetail();
    }
}
