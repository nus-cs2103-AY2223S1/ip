import exception.NoSuchTaskException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    public enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    List<Task> taskList;

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
        taskList = new ArrayList<>();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke\n" +
                "What can I do for you?"
        );
        loop(sc);
    }

    public void loop(Scanner sc) {
        String input = sc.nextLine().trim();

        if ("bye".equals(input)) {
            System.out.println("Bye. Hope to see you again soon!");
            return;
        } else if ("list".equals(input)) {
            System.out.println("Here are the tasks in your list:");
            System.out.print(inputListToString());
        } else if (Pattern.matches("mark \\d+", input)) {
            int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
            Task taskToMark = taskList.get(index);
            taskToMark.mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskToMark);
        } else if (Pattern.matches("unmark \\d+", input)) {
            int index = Integer.parseInt(input.split("\\s+")[1]) - 1;
            Task taskToUnmark = taskList.get(index);
            taskToUnmark.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskToUnmark);
        } else {
            try {
                TaskTypes type = getTaskType(input);
                int i = input.indexOf(' ');
                String taskString = input.substring(i + 1);
                Task newTask = addNewTask(taskString, type);
                String response = String.format("Got it. I've added this task:\n  " +
                        "%s\nNow you have %d tasks in the list.", newTask, taskList.size());
                System.out.println(response);
            } catch (NoSuchTaskException e) {
                System.out.println("Error: " + e);
            }
        }

        loop(sc);
    }

    private String inputListToString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task t : taskList) {
            sb.append(i);
            sb.append(". ");
            sb.append(t);
            sb.append("\n");
            ++i;
        }
        return sb.toString();
    }

    private Task addNewTask(String taskString, TaskTypes type) {
        String[] taskInfo;
        Task newTask;

        switch (type) {
            case TODO:
                newTask = new Todo(taskString);
                break;
            case EVENT:
                taskInfo = taskString.split("/at");
                newTask = new Event(taskInfo[0].trim(), taskInfo[1].trim());
                break;
            case DEADLINE:
                taskInfo = taskString.split("/by");
                newTask = new Deadline(taskInfo[0].trim(), taskInfo[1].trim());
                break;
            default:
                return null;
        }

        taskList.add(newTask);
        return newTask;
    }

    private TaskTypes getTaskType(String input) throws NoSuchTaskException {
        int i = input.indexOf(' ');
        String type = input.substring(0, i);

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