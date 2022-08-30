package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Handles all the storage-related tasks.
 */
public class Storage {
    private final String folderDirectory;
    private final String fileDirectory;

    /**
     * Initializes a new Storage instance.
     * @param folderDirectory Directory of the persistent storage file
     * @param fileName Name of the persistent storage file
     */
    public Storage(String folderDirectory, String fileName) {
        this.folderDirectory = folderDirectory;
        this.fileDirectory = folderDirectory + "/" + fileName + ".txt";
    }

    /**
     * Reads and parses the task list from persistent storage and returns it.
     * If no existing storage file is found, this method returns a new empty TaskList.
     *
     * @return TaskList from persistent storage.
     */
    public TaskList readFromStorage() {
        TaskList taskList = new TaskList();
        try {
            File f = new File(this.fileDirectory);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String taskStr = sc.nextLine();
                String[] taskStrTokens = taskStr.split("\\|");

                String taskType = taskStrTokens[0];
                String taskDescription = taskStrTokens[1];
                boolean isTaskDone = taskStrTokens[2].equals("0") ? false : true;

                switch (taskType) {
                case "Todo":
                    Todo currTodo = new Todo(taskDescription, isTaskDone);
                    taskList.addTask(currTodo);
                    break;
                case "Deadline":
                    LocalDateTime by = LocalDateTime.parse(taskStrTokens[3]);
                    Deadline currDeadline = new Deadline(taskDescription, isTaskDone, by);
                    taskList.addTask(currDeadline);
                    break;
                case "Event":
                    LocalDateTime at = LocalDateTime.parse(taskStrTokens[3]);
                    Event currEvent = new Event(taskDescription, isTaskDone, at);
                    taskList.addTask(currEvent);
                    break;
                default:
                    // Something that cannot be recognized
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            return taskList;
        }
        return taskList;
    }

    private static String taskStrRepresentation(Task task) {
        String taskType = task.getTaskType();
        String taskDescription = task.getDescription();
        String taskDone = task.getDone() ? "1" : "0";
        String taskTime = task.getTime().map(LocalDateTime::toString).orElse(" ");

        String strRepresentation = taskType + "|" + taskDescription + "|" + taskDone + "|" + taskTime + "\n";
        return strRepresentation;
    }

    /**
     * Creates a new, blank persistent storage file and writes the input duke.utils.TaskList into the file.
     *
     * @param taskList Input TaskList to read from and write into the persistent storage file.
     */
    public void writeAllToStorage(TaskList taskList) {
        File folderDirectory = new File(this.folderDirectory);
        if (!folderDirectory.exists()) {
            folderDirectory.mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(this.fileDirectory, false);
            int lenTaskList = taskList.size();
            for (int i = 0; i < lenTaskList; i++) {
                Task task = taskList.getTask(i);
                String strRepresentation = Storage.taskStrRepresentation(task);
                fw.append(strRepresentation);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Appends a new Task to persistent storage.
     */
    public void appendTaskToStorage(Task task) {
        File folderDirectory = new File(this.folderDirectory);
        if (!folderDirectory.exists()) {
            folderDirectory.mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(this.fileDirectory, true);
            String strRepresentation = Storage.taskStrRepresentation(task);
            fw.write(strRepresentation);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
