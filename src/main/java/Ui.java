import java.util.ArrayList;

public class Ui {
    public static void printWelcomeMessage() {
        System.out.println("Hello from Botto\nWhat can I do for you?");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printTaskAdded(Task task, int taskNumber) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
    }

    public static void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        taskList.printSelf();
    }

    public static void printDeletedTask(Task task, int taskLeft) {
        System.out.println("Noted. I have removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskLeft + " tasks in the list.");
    }

    public static void printMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public static void printUnmarkedTask(Task task){
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public static void printDukeError(DukeException e) {
        System.out.println(e.getMessage());
    }
}
