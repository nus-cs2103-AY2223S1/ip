package sally.ui;

import sally.task.TaskList;

import java.util.Scanner;

/**
 * Ui class where all ui is handled.
 *
 * @author liviamil
 */

public class Ui {
    protected String BORDER ="-------------------------------------------------------------------------------------";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printWithBorder(String s) {
        System.out.println(BORDER);
        System.out.println(s);
        System.out.println(BORDER);
    }

    public void showGreeting() {
        printWithBorder("Hello! I'm Sally\nWhat can I do for you?");
    }

    public String getUserCommand() {
        return sc.nextLine();
    }

    public String getList(TaskList tasks) {
        String output = "";
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            int number = i + 1;
            output = output + number + ". " + tasks.getTask(i).toString() + "\n";
        }
        return output;
    }

    public void showList(TaskList tasks) {
        if (tasks.getNumOfTasks() == 0) {
            printWithBorder("You don't have any list right now");
        } else {
            printWithBorder("Here's your current list:\n" + getList(tasks));
        }
    }

    public void showDeleted(String removed) {
        printWithBorder("This task has been removed from your to-do list:\n" + removed);
    }

    public void showUnmarked(String unmarkedTask) {
        printWithBorder("Got it, I've unmarked this task for you!\n" + unmarkedTask);
    }

    public void showPreviouslyUnmarked(String notMarked) {
        printWithBorder("You have not marked: \n  " + notMarked);
    }

    public void showMarked(String markedTask) {
        printWithBorder("Got it, I've marked this task for you!\n" + markedTask);
    }

    public void showPreviouslyMarked(String marked) {
        printWithBorder("You have previously done: \n" + marked);
    }

    public void showAddedTask(TaskList tasks) {
        int taskNum = tasks.getNumOfTasks();
        String message = (taskNum == 1)
            ? "Now you have 1 task in your list."
            : "Now you have " + taskNum + " tasks in your list.";
        printWithBorder("Got it. I've added this task:\n" + tasks.getTask(taskNum - 1).toString() + "\nto your list! " + message);
    }

    public void showGoodbye() {
        printWithBorder("Ok, until next time!");
    }

    public void showError(String error) {
        printWithBorder(error);
    }
}
