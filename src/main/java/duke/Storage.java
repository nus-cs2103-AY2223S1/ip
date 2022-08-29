package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class that handles saving and loading
 * tasks in a file on the hard drive
 */
public class Storage {
    private String fileLocation;
    private static final int ISDONE_MARKER = 4;
    private static final int DESC_MARKER = 8;

    /**
     * Creates a storage object
     */
    public Storage(String filePath) {
        this.fileLocation = filePath;
    }

    /**
     * Loads tasks from a task file
     * into an ArrayList of tasks
     */
    public ArrayList<Task> loadTaskFile() {
        File taskFile = new File(fileLocation);
        ArrayList<Task> tasks = new ArrayList<Task>();

        if (!taskFile.exists()) {
            File directory = new File(taskFile.getParent());
            directory.mkdir();
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                char TaskType = line.charAt(0);
                boolean taskIsDone = line.charAt(ISDONE_MARKER) == '1';
                String taskDesc;
                String taskTime;

                switch (TaskType) {
                case 'T':
                    taskDesc = line.substring(DESC_MARKER).trim();
                    tasks.add(new ToDo(taskDesc, taskIsDone));
                    break;

                case 'D':
                    taskDesc = line.substring(DESC_MARKER, line.indexOf('(')).trim();
                    taskTime = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();

                    tasks.add(new Deadline(taskDesc, taskIsDone, taskTime));
                    break;

                case 'E':
                    taskDesc = line.substring(DESC_MARKER, line.indexOf('(')).trim();
                    taskTime = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();

                    tasks.add(new Event(taskDesc, taskIsDone, taskTime));
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }


    /**
     * Saves the ArrayList of tasks from
     * a taskList object into a task file
     */
    public void saveTaskFile(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            FileWriter taskFile = new FileWriter(fileLocation);
            String textToSave = " ";
            for (int i = 0; i < tasks.size(); i++) {
                textToSave += tasks.get(i).toStringData() + "\n";
            }
            taskFile.write(textToSave.trim());
            taskFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
