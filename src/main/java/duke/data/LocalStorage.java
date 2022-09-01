package duke.data;

import duke.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static duke.services.Ui.dukePrint;

public class LocalStorage {
    private final static String DIR_PATH = "./data";
    private final static String FILE_PATH = "./data/duke.txt";
    private static final Pattern TASK_REGEX = Pattern.compile("^\\[(T|D|E)\\]\\[(X| )\\] (.*?)(?: \\(.*: (.*)\\))?$");

    /**
     * Writes a list of tasks into a .txt file as specified by FILE_PATH.
     * @param tasklist: The ArrayList of tasks to be written.
     **/
    public void write(ArrayList<Task> tasklist) {
        try {
            File dukeFile = new File(FILE_PATH);
            dukeFile.createNewFile();
        } catch (IOException exception) {
            dukePrint(String.format("Error creating file at path %s", FILE_PATH));
        }

        try {
            FileWriter myWriter = new FileWriter(FILE_PATH);
            for (Task task: tasklist) {
                myWriter.write(task.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            dukePrint(String.format("Unable to write to file: %s", FILE_PATH));
        }
    }

    /**
     * Loads Tasks from a .txt file as specified by FILE_PATH.
     * Tasks should follow the following REGEX "^\\[(T|D|E)\\]\\[(X| )\\] (.*?)(?: \\(.*: (.*)\\))?$".
     * @return TaskList: TaskList generated from the Tasks.
     **/
    public TaskList load() {
        try {
            TaskList result = new TaskList();
            File storageDirectory = new File(DIR_PATH);
            if (!storageDirectory.exists()) {
                storageDirectory.mkdirs();
            }
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            int lineNumber = 1;
            while((line = reader.readLine()) != null){
                try {
                    Matcher m = TASK_REGEX.matcher(line);
                    m.find();
                    String taskType = m.group(1);
                    String taskDone = m.group(2);
                    String taskName = m.group(3);
                    String taskDate = m.group(4);
                    switch (taskType) {
                        case "T":
                            result.add(new Todo(taskName, taskDone.equalsIgnoreCase("X")));
                            break;
                        case "D":
                            result.add(new Deadline(taskName, taskDone.equalsIgnoreCase("X"), taskDate));
                            break;
                        case "E":
                            result.add(new Event(taskName, taskDone.equalsIgnoreCase("X"), taskDate));
                            break;
                    }
                    lineNumber++;
                } catch (IllegalStateException ex) {
                    dukePrint(String.format("Invalid line found at line %d", lineNumber));
                }
            }
            return result;
        } catch (IOException ex) {
            dukePrint(String.format("File (%s) not found! Starting from empty ArrayListTask.", FILE_PATH));
            return new TaskList();
        }
    }
}
