package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Deadline;
import entities.Event;
import entities.Task;
import entities.Todo;

/**
 * Handles storing and loading tasks from the hard disk between sessions.
 */
public class Storage {
    /**
     * The path at which the target file can be found / should be created.
     */
    private String filePath;

    /**
     * The path at which the target directory can be found / should be created.
     */
    private String directoryPath;

    /**
     * Constructor for a Storage instance.
     * @param filePath The path at which the target file can be found / should be created.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.directoryPath = filePath.substring(0, filePath.lastIndexOf("/"));
    }

    /**
     * Saves the current tasks into the specified text file.
     * Overwrites the entire text file with a modified string representing
     * all the current tasks.
     *
     * @param taskList The TaskList which contains all the tasks entered by the user.
     */
    public void save(TaskList taskList) {
        // Create directory if it doesn't exist
        File dir = new File(this.directoryPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // Create file if it doesn't exist
        File file = new File(filePath);
        if (dir.exists() && !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Overwrite file contents with latest task data
        try {
            FileWriter fw = new FileWriter(file.getPath());
            fw.write(taskList.formatAllTasksForFileStorage());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a TaskList which contains the tasks entered by the user
     * based on the previous session.
     *
     * @return A TaskList which contains the cached tasks entered by the user
     *         during the previous session.
     */
    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();

        // Check if directory and file exists
        File dir = new File(this.directoryPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // Create file if it doesn't exist
        File file = new File(filePath);
        if (dir.exists() && !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            // Load the data into the task array
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] taskData = sc.nextLine().split(" \\| ");
                String taskType = taskData[0];
                boolean isDone = taskData[1].equals("d");
                String desc = taskData[2];
                String remarks = "";
                if (taskData.length == 4) {
                    remarks = taskData[3];
                }
                switch (taskType) {
                case "todo":
                    Todo t = new Todo(desc);
                    if (isDone) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                    break;
                case "event":
                    Event e = new Event(desc, remarks);
                    if (isDone) {
                        e.markAsDone();
                    }
                    taskList.add(e);
                    break;
                case "deadline":
                    Deadline d = new Deadline(desc, remarks);
                    if (isDone) {
                        d.markAsDone();
                    }
                    taskList.add(d);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }
}
