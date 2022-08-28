package duke;

import java.util.ArrayList;
import java.util.List;

/**
* UI class to handle all user interactions and system output.
*
* @author Sheryl Kong (A0240686Y)
*/

public class UI {
    public static final String DIVIDER = "––––––––––––––––––––––––––––––––––––––––––-––––––––––––––––––––––––-–––––––––––––––––––––––––––––––––";

    public static void welcome() {
        System.out.println("Hello! I am Sheryl. Please enter your command: ");
    }

    public static void bye() { System.out.println("Bye. Hope to see you again soon!"); }

    public static void added(Task task) {
        System.out.println(DIVIDER);
        System.out.println(task.added());
        System.out.println(DIVIDER);
    }

    public static void delete(Task task) {
        System.out.println(DIVIDER);
        System.out.printf("Noted. I've removed this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", task, Task.getTaskCount());
        System.out.println(DIVIDER);
    }

    public static void list(TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.printf("Here are the tasks in your list:\n");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.printf(" %d. %s\n", i + 1, taskList.getTask(i));
        }
        System.out.println(DIVIDER);
    }

    public static void markAsDone(Task task) {
        System.out.println(DIVIDER);
        System.out.printf("Nice! I've marked this task as done: \n" +
                            "%s\n", task);
        System.out.println(DIVIDER);
    }

    public static void markAsUndone(Task task) {
        System.out.println(DIVIDER);
        System.out.printf("OK, I've marked this task as not done yet: \n" +
                            "%s\n", task);
        System.out.println(DIVIDER);
    }

    public static void find(TaskList tasks, String desc) {
        System.out.println(DIVIDER);
        System.out.printf("Here are the matching tasks in your list:\n");
        int count = 1;
        for(Task task : tasks.getList()) {
            if (task.toString().contains(desc)) {
                System.out.printf(" %d. %s\n", count, tasks.getTask(count));
                count++;
            }
        }
        System.out.println(DIVIDER);
    }
}
