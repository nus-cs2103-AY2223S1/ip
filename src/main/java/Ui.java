import java.util.ArrayList;

public class Ui {
    private static final String DIVIDER = "-------------------------------------\n";

    // greet method contains the greeting message
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(DIVIDER + message + DIVIDER);
    }

    public static void exit() {
        System.out.println(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }

    public static void listPrint(ArrayList<Task> input) {
        if (input.isEmpty()) {
            System.out.println(DIVIDER + "List is empty\n" + DIVIDER);
        } else {
            System.out.print(DIVIDER);
            for (int i = 0; i < input.size(); i++) {
                System.out.println((i+1) + ". " + input.get(i));
            }
            System.out.println(DIVIDER);
        }
    }

    public static void addTask(String type, Task currTask, int size) {
        System.out.printf(DIVIDER + "OK, I've added this %s:\n %s\n"
                +"Number of tasks in list: %d\n" + DIVIDER + "\n",
                type, currTask, size);
    }

    public static void deleteTask(Task currTask, int size) {
        System.out.printf(DIVIDER + "OK, I've removed this task:\n"
                +"  %s \nNumber of tasks in list: %d\n" + DIVIDER + "\n",
                currTask, size);
    }

    public static void toggleTask(Task currTask) {
        if(currTask.isCompleted()) {
            System.out.println(DIVIDER + "Nice! I've marked this task as done:\n"
                    + "  " + currTask + "\n" + DIVIDER);
        } else {
            System.out.println(DIVIDER + "OK, I've marked this task as not done yet:\n"
                    + "  " + currTask + "\n" + DIVIDER);
        }
    }

    public static void printException(DukeException e) {
        System.out.println(DIVIDER + e.getMessage() + DIVIDER);
    }
}
