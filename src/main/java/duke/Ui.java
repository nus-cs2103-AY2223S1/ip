package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Encapsulates the Ui for Duke.
 */
public class Ui {

    private boolean cont = true;

    public boolean isActive() {
        return this.cont;
    }

    public void printSpacer() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void greet() {
        printSpacer();
        System.out.println(":D Hello! This is Duke! What can I do for you today?");
        printSpacer();
    }

    public void goodbye() {
        System.out.println(":) Bye. Hope to see you again soon!");
        printSpacer();
        cont = false;
    }

    public void printSuccessfulClear() {
        System.out.println("Your task list and save file have been cleared!");
    }

    public void printSuccessfulMark() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void printSuccessfulUnmark() {
        System.out.println("Okie, I've marked this task as not done yet:");
    }

    public void printSuccessfulAdd() {
        System.out.println("Got it. I've added this task:");
    }

    public void printSuccessfulDelete() {
        System.out.println("Okie, I've deleted this task:");
    }

    public void printNoOfTasks(TaskList ts) {
        System.out.println("Now you have " + ts.getTasksLength() + " tasks in your list.");
    }

    public void printTaskList(TaskList ts) {
        if (ts.getTasksLength() == 0) {
            System.out.println("No tasks yet~");
        } else {
            System.out.println("Here are the tasks in your list:");
            ts.printAllTasks();
        }
        printSpacer();
    }

    public void printTask(Task t) {
        System.out.println(t.toString());
    }

    public void printFoundResults(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    public void printErrorMessage(Exception e, TaskList ts) {
    /**
     * Prints the corresponding error message when Duke encounters an error.
     *
     * @param e the Exception encountered
     * @param tasks the relevant TaskList that may have caused the exception
     */
    public void printErrorMessage(Exception e, TaskList tasks) {
        if (e instanceof IOException) {
            System.out.println(":( An error occurred reading/writing your save files!");
            printSpacer();
        } else if (e instanceof InvalidCommandException) {
            System.out.println(":( Sorry I don't understand the command: " + e);
            printSpacer();
        } else if (e instanceof TaskNumberException) {
            System.out.println(":( Oops! Please enter a valid task number!");
            System.out.println("You currently have " + tasks.getTasksLength() + " tasks.");
            printSpacer();
        } else if (e instanceof EmptyTodoException) {
            System.out.println(":( Oops! The description of a todo cannot be empty!");
            printSpacer();
        } else if (e instanceof DeadlineFormatException) {
            System.out.println(":( Oops! That's not the right way to set a deadline!");
            System.out.println("Please use this format: \"deadline <description> /by <time>\"");
            printSpacer();
        } else if (e instanceof EventFormatException) {
            System.out.println(":( Oops! That's not the right way to set an event!");
            System.out.println("Please use this format: \"event <description> /at <time>\"");
            printSpacer();
        } else if (e instanceof EmptyFindException) {
            System.out.println(":( Oops! The search keyword(s) cannot be empty!");
            printSpacer();
        } else {
            System.out.println(":( Oops! An unknown error has occurred!");
            printSpacer();
            e.printStackTrace();
        }
    }
}
