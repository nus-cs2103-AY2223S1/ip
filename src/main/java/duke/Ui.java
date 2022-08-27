package duke;

import java.util.Scanner;

public class Ui {

    public final String ADDED = "oke i added this:";
    public final String DELETED = "oke i deleted this:";
    public final String MARKED = "oke this is done now:";
    public final String UNMARKED = "oke this is undone now:";

    Scanner scanner;

    public Ui () {
        System.out.println("----------------------");
    }

    public void showWelcome() {
        System.out.println("hi im chompers what can i do for u today!");
        scanner = new Scanner(System.in);
    }

    public void showExit() {
        System.out.println("bye see u");
        scanner.close();
    }

    public void displayTask(String message, Task task) {
        System.out.println(message);
        System.out.println(task);
    }

    public void printTasks(TaskList taskList) {
        System.out.println("here! ur tasks:");
        System.out.println(taskList.toString());

    }

    public void showTotalTasks(TaskList taskList) {
        System.out.println("now u have " + taskList.getSize() + " task(s)!");
    }

    /**
     * Displays the matching tasks for the user.
     *
     * @param taskList Tasklist to be displayed.
     */
    public void showMatchingTasks(TaskList taskList) {
        if(taskList.getSize() > 0) {
            System.out.println("here are the matching tasks:");
            System.out.println(taskList);
        } else {
            System.out.println("there are no tasks matching this keyword!");
        }
    }
    public void showError(String message) {
        System.out.println("error! " + message);
    }

    public String readCommand() {
        String str;
        scanner = new Scanner(System.in);
        str = scanner.nextLine();
        return str;
    }
}
