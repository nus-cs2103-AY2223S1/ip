import exception.EmptyTaskDescException;
import exception.EmptyTaskTimeException;
import exception.NoSuchTaskException;
import tasks.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    private List<Task> taskList;
    private SaveData data;
    private static final File savefile = new File("savedata.txt");

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();
    }

    public Duke() {

    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke\n" +
                "What can I do for you?"
        );
        data = new SaveData();
        boolean isSaveFileCreated = savefile.exists();
        if (!isSaveFileCreated) {
            try {
                isSaveFileCreated = savefile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error while creating save file: " + e);
            }
        }

        if (!isSaveFileCreated) {
            System.out.println("Unable to find or create save file, exiting...");
            System.exit(1);
        }

        data.loadFromFile(new File("savedata.txt"));
        taskList = data.getTaskList();
        loop(sc);
    }

    public void loop(Scanner sc) {
        while (sc.hasNext()) {
            String input = sc.nextLine().trim();
            int spaceIndex = input.indexOf(' ');
            String command, body;
            if (spaceIndex == -1) {
                command = input;
                body = "";
            } else {
                command = input.substring(0, spaceIndex);
                body = input.substring(spaceIndex + 1);
            }

            boolean isModified = false;
            if ("bye".equals(command)) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if ("list".equals(command)) {
                printTaskList();
            } else if ("mark".equals(command)) {
                markTask(body);
                isModified = true;
            } else if ("unmark".equals(command)) {
                unmarkTask(body);
                isModified = true;
            } else if ("delete".equals(command)) {
                deleteTask(body);
                isModified = true;
            } else if (isTaskType(command)) {
                addTask(command, body);
                isModified = true;
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            if (isModified) {
                data.setTaskList(taskList);
                data.saveToFile(savefile);
            }
        }
    }

    private void markTask(String body) {
        int index = Integer.parseInt(body) - 1;
        Task taskToMark = taskList.get(index);
        taskToMark.mark();
        String response = String.format(
                "Nice! I've marked this task as done:\n  %s",
                taskToMark);
        System.out.println(response);
    }

    private void unmarkTask(String body) {
        int index = Integer.parseInt(body) - 1;
        Task taskToUnmark = taskList.get(index);
        taskToUnmark.unmark();
        String response = String.format(
                "OK, I've marked this task as not done yet:\n  %s",
                taskToUnmark
        );
        System.out.println(response);
    }

    private void deleteTask(String body) {
        int index = Integer.parseInt(body) - 1;
        Task deletedTask = taskList.remove(index);
        String response = String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list",
                deletedTask, taskList.size()
        );
        System.out.println(response);
    }

    private void addTask(String command, String body) {
        try {
            TaskTypes type = getTaskType(command);
            Task newTask = createNewTask(body, type);
            String response = String.format(
                    "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newTask, taskList.size());
            System.out.println(response);
        } catch (NoSuchTaskException | EmptyTaskDescException | EmptyTaskTimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task t : taskList) {
            sb.append(i);
            sb.append(". ");
            sb.append(t);
            sb.append("\n");
            ++i;
        }
        System.out.print(sb);
    }

    private boolean isTaskType(String s) {
        return "todo".equals(s) ||
                "event".equals(s) ||
                "deadline".equals(s);
    }

    private Task createNewTask(String taskString, TaskTypes type)
            throws EmptyTaskDescException, EmptyTaskTimeException {
        if ("".equals(taskString)) {
            throw new EmptyTaskDescException();
        }

        String[] taskInfo;
        Task newTask;

        switch (type) {
            case TODO:
                newTask = new Todo(taskString);
                break;
            case EVENT:
                taskInfo = taskString.split("/at");
                if (taskInfo.length < 2) {
                    throw new EmptyTaskTimeException();
                }
                newTask = new Event(taskInfo[0].trim(), taskInfo[1].trim());
                break;
            case DEADLINE:
                taskInfo = taskString.split("/by");
                if (taskInfo.length < 2) {
                    throw new EmptyTaskTimeException();
                }
                newTask = new Deadline(taskInfo[0].trim(), taskInfo[1].trim());
                break;
            default:
                return null;
        }

        taskList.add(newTask);
        return newTask;
    }

    private TaskTypes getTaskType(String type) throws NoSuchTaskException {
        switch(type) {
            case "todo":
                return TaskTypes.TODO;
            case "event":
                return TaskTypes.EVENT;
            case "deadline":
                return TaskTypes.DEADLINE;
            default:
                throw new NoSuchTaskException(type);
        }
    }
}