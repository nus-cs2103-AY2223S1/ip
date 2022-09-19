package duke.storage;

import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;
import duke.models.TaskList;

import java.io.*;
import java.util.ArrayList;
import static duke.services.Ui.dukePrint;

public class Storage {
    public static final String DIR_PATH = "./data";
    public static final String FILE_PATH = "./data/duke.txt";

    /**
     * Saves the list of task in a tasklist into a .txt file as specified by the FILE_PATH.
     * @param taskList: ArrayList of tasks to save to the file.
     **/
    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : taskList) {
                fw.write(task.toSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            dukePrint(String.format("Unable to write to file at (%s)", FILE_PATH));
        }
    }

    /**
     * Loads tasks from a .txt file as specified by FILE_PATH.
     * @return TaskList of tasks saved in the .txt file.
     **/
    public TaskList load() {
        try {
            TaskList taskList = new TaskList();
            File file = new File(DIR_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                try {
                    String[] lineElem = nextLine.split("\\| ");
                    if (lineElem[0].trim().equals("T")) {
                        taskList.add(new Todo(lineElem[2]));
                        if (lineElem[2].trim().equals("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        } else {
                            taskList.get(taskList.size() - 1).setUndone();
                        }
                    } else if (lineElem[0].trim().equals("D")) {
                        taskList.add(new Deadline(lineElem[2], lineElem[3]));
                        if (lineElem[1].trim().equals("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        } else {
                            taskList.get(taskList.size() - 1).setUndone();
                        }
                    } else if (lineElem[0].trim().equals("E")) {
                        taskList.add(new Event(lineElem[2], lineElem[3]));
                        if (lineElem[1].trim().equals("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        } else {
                            taskList.get(taskList.size() - 1).setUndone();
                        }
                    }
                } catch (IllegalStateException e) {
                    dukePrint("Invalid line");
                }
            }
            return taskList;
        } catch (IOException e) {
            dukePrint(String.format("File (%s) not found!", FILE_PATH));
            return new TaskList();
        }
    }
}
