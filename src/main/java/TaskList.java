import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

public class TaskList {
    private static final Path TASKLIST_PATH = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");

    public static void addTaskToTaskListText(String taskDescription) {
        try {
            Files.writeString(TASKLIST_PATH, taskDescription);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Task> parseTaskListText() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            BufferedReader reader = Files.newBufferedReader(TASKLIST_PATH);
            String nextLine = reader.readLine();
            Task nextTask;

            while (nextLine != null) {
                String[] actionDescription = nextLine.split(", ");
                boolean isFinished = actionDescription[1].equals("finished");
                String taskName = actionDescription[2];

                if (actionDescription[0].equals("[T]")) { // Todo task
                    nextTask = new Task.ToDo(taskName);
                } else { // Timed task
                    String timing = actionDescription[3];
                    if (actionDescription[0].equals("[D]")) { // Deadline task
                        nextTask = new TimedTask.Deadline(taskName, timing);
                    } else { // Event task
                        nextTask = new TimedTask.Event(taskName, timing);
                    }
                }

                if (isFinished) {
                    nextTask.setFinished();
                }

                taskList.add(nextTask);

                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return taskList;
    }

    public static void updateTaskListText(ArrayList<Task> taskList) {
        String newTaskListText = "";

        for (Task task: taskList) {
            newTaskListText += task.showTaskListTextDescription();
        }
        try {
            Files.writeString(TASKLIST_PATH, newTaskListText);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
