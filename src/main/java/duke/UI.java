package duke;

import java.util.ArrayList;
import java.util.List;

/**
* UI class to handle all user interactions and system output.
*
* @author Sheryl Kong (A0240686Y)
*/

public class UI {
    public static final String DIVIDER = "–––––––––––––––––––––––\n";

    public static void response(String text) {
        System.out.println(text);
    }

    public static String getResponse(String text) { return text; }

    public static void welcome() {
        System.out.println("Hello! I am Duke. Please enter your command: ");
    }

    public static String welcomeResponse() {
        return "Hello! I am Duke. Please enter your command: ";
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String byeResponse() {
        return "Bye. Hope to see you again soon!";
    }

    public static void invalid() {
        System.out.println("OOPS!! Please enter a valid command!");
    }

    public static String invalidResponse() {
        return "OOPS!! Please enter a valid command!";
    }


    public static void added(Task task) {
        System.out.println(DIVIDER);
        System.out.println(task.added());
        System.out.println(DIVIDER);
    }

    public static String addedResponse(Task task) {
        return DIVIDER
                + task.added() + System.lineSeparator()
                + DIVIDER;
    }

    public static void delete(Task task) {
        System.out.println(DIVIDER);
        System.out.printf("Noted. I've removed this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", task, Task.getTaskCount());
        System.out.println(DIVIDER);
    }

    public static String deleteResponse(Task task) {
        return DIVIDER
                + "Noted. I've removed this task:\n"
                + task + System.lineSeparator()
                + String.format("Now you have %d tasks in the list.\n", Task.getTaskCount())
                + DIVIDER;
    }

    public static void list(TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.printf("Here are the tasks in your list:\n");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.printf(" %d. %s\n", i + 1, taskList.getTask(i));
        }
        System.out.println(DIVIDER);
    }

    public static String listResponse(TaskList taskList) {
        String tasks = "";
        for (int i = 0; i < Task.getTaskCount(); i++) {
            tasks += String.format(" %d. %s\n", i + 1, taskList.getTask(i));
        }
        return DIVIDER
                + "Here are the tasks in your list:\n"
                + tasks
                + DIVIDER;
    }

    public static void markAsDone(Task task) {
        System.out.println(DIVIDER);
        System.out.printf("Nice! I've marked this task as done: \n" +
                            "%s\n", task);
        System.out.println(DIVIDER);
    }

    public static String markAsDoneResponse(Task task) {
        return DIVIDER
                + "Nice! I've marked this task as done: \n"
                + task + System.lineSeparator()
                + DIVIDER;
    }

    public static void markAsUndone(Task task) {
        System.out.println(DIVIDER);
        System.out.printf("OK, I've marked this task as not done yet: \n" +
                            "%s\n", task);
        System.out.println(DIVIDER);
    }

    public static String markAsUndoneResponse(Task task) {
        return DIVIDER
                + "OK, I've marked this task as not done yet: \n"
                + task + System.lineSeparator()
                + DIVIDER;
    }

    public static void find(TaskList tasks, String desc) {
        System.out.println(DIVIDER);
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for(Task task : tasks.getList()) {
            if (task.toString().contains(desc)) {
                System.out.printf(" %d. %s\n", count, task);
                count++;
            }
        }
        System.out.println(DIVIDER);
    }

    public static String findResponse(TaskList tasks, String desc) {
        String matchedTasks = " ";
        int count = 1;
        for(Task task : tasks.getList()) {
            if (task.toString().contains(desc)) {
                matchedTasks += String.format(" %d. %s\n", count, task);
                count++;
            }
        }

        return DIVIDER
                + "Here are the matching tasks in your list: \n"
                + matchedTasks
                + DIVIDER;
    }
}
