import java.util.ArrayList;
public class Ui {
    public Ui() {

    }

    public static void showLoadingError() {
        System.out.println("No save file to load from");
    }

    public static void startMessage() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void deleteTask(Task task) {
        System.out.println("Noted. I've removed this task:" + "\n" + "\t" + task.toString());
    }

    public static void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done:" + "\n" + "\t" + task.toString());
    }

    public static void unmarkTask(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:" + "\n" + "\t" + task.toString());
    }

    public static void addTask(Task task) {
        System.out.println("Got it. I've added this task:" + "\n\t" + task.toString());
    }

    public static void numOfTasks(int numOfTasks) {
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }
}
