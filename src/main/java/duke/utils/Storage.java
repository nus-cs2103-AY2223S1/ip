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

public class Storage {
    /**
     * Reads and parses the task list from persistent storage and returns it.
     *
     * @return duke.utils.TaskList from persistent storage.
     */
    public static TaskList readFromStorage() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File f = new File("data/duke.txt");
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
     * @param taskList Input duke.utils.TaskList to read from and write into the persistent storage file.
     */
    public static void writeAllToStorage(TaskList taskList) {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        try {
            FileWriter fw = new FileWriter("data/duke.txt", false);
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
     * Appends a new duke.tasks.Task to persistent storage.
     */
    public static void appendTaskToStorage(Task task) {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        try {
            FileWriter fw = new FileWriter("data/duke.txt", true);
            String strRepresentation = Storage.taskStrRepresentation(task);
            fw.write(strRepresentation);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
