import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private static final Path TASKLIST_PATH = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");

    public static void addTaskToTaskListText(String taskDescription) {
        try {
            Files.writeString(TASKLIST_PATH, taskDescription, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Task> parseTaskListText() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            BufferedReader reader = Files.newBufferedReader(TASKLIST_PATH);
            String nextLine = reader.readLine();
            System.out.println("problem:" + nextLine);
            Task nextTask;

            while (nextLine != null) {
                String[] taskDescription = nextLine.split(", ");
                boolean isFinished = taskDescription[1].equals("finished");
                String taskName = taskDescription[2];

                if (taskDescription[0].equals("[T]")) { // Todo task
                    nextTask = new Task.ToDo(taskName);
                } else { // Timed task
                    String dateAsString = taskDescription[3];
                    LocalDate date = LocalDate.parse(dateAsString);

                    if (taskDescription[0].equals("[D]")) { // Deadline task
                        String deadlineTimeAsString = taskDescription[4];
                        LocalTime deadlineTime = deadlineTimeAsString.equals("no time given")
                                             ? null
                                             : LocalTime.parse(deadlineTimeAsString);
                        nextTask = new TimedTask.Deadline(taskName, date, deadlineTime);
                    } else { // Event task
                        LocalTime eventStartTime;
                        LocalTime eventEndTime;

                        if (taskDescription[4].equals("no time given")) {
                            eventStartTime = null;
                            eventEndTime = null;
                        } else {
                            eventStartTime = LocalTime.parse(taskDescription[4]);
                            eventEndTime = LocalTime.parse(taskDescription[5]);
                        }

                        nextTask = new TimedTask.Event(taskName, date, eventStartTime, eventEndTime);
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
        StringBuilder newTaskListText = new StringBuilder();

        for (Task task: taskList) {
            newTaskListText.append(task.showTaskListTextDescription());
        }
        try {
            Files.writeString(TASKLIST_PATH, newTaskListText.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
