import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private boolean isActive;
    public Ui() {
        scanner = new Scanner(System.in);
        isActive = true;
    }

    public String retrieveUserInput() {
        return scanner.nextLine();
    }

    public boolean isScannerActive() {
        return isActive;
    }

    public void printIntro() {
        printDivider();
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        printDivider();
    }
    public void printOutro() {
        isActive = false;
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public void printError(String msg) {
        System.out.println(msg);
        printDivider();
    }

    public void printMarkTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:" + "\n  " + task);
        printDivider();
    }

    public void printMarkTaskUndone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:" + "\n  " + task);
        printDivider();
    }

    public void printDeletedTask(TaskList list, Task task) {
        System.out.println("Noted. I've removed this task:" + "\n  " + task
                + "\nNow you have " + list.getListSize() + " tasks in the list.");
        printDivider();
    }
    public void printAddedTask(TaskList list, Task task) {
        System.out.println("Got it. I've added this task:\n  " + task
                + "\nNow you have " + list.getListSize() + " tasks in the list.");
        printDivider();
    }

    public void printActiveTasks(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.getListSize(); i++) {
            System.out.println((i + 1) + "." + list.retrieveTask(i));
        }
        printDivider();
    }

    public void printDueTasks(ArrayList<Task> list) {
        System.out.println("Here are the tasks due at this date:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        printDivider();
    }
    private void printDivider() {
        System.out.println("_________________________________________________________________");
    }

}
