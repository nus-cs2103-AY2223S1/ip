package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Encapsulates the Ui for Duke.
 */
public class Ui {

    private boolean isActive = true;

    public boolean isActive() {
        return this.isActive;
    }

    public void greet() {
        System.out.println(":D Hello! This is Duke! What can I do for you today?");
    }

    public void goodbye() {
        System.out.println(":) Bye. Hope to see you again soon!");
        isActive = false;
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
        System.out.println("Okie. I've added this task:");
    }

    public void printSuccessfulDelete() {
        System.out.println("Okie, I've deleted this task:");
    }

    public void printSuccessfulUpdate() {
        System.out.println("Okie, I've updated this task:");
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
    }

    public void printTask(Task t) {
        System.out.println(t.toString());
    }

    public void printFoundResults(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("Didn't find any matching tasks~");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + "." + tasks.get(i - 1).toString());
            }
        }
    }

    /**
     * Prints the corresponding error message when Duke encounters an error.
     *
     * @param e the Exception encountered
     * @param tasks the relevant TaskList that may have caused the exception
     */
    public void printErrorMessage(Exception e, TaskList tasks) {
        if (e instanceof IOException) {
            System.out.println(":( An error occurred reading/writing your save files!");
        } else if (e instanceof InvalidCommandException) {
            System.out.println(":( Sorry, I don't understand the command: " + e);
        } else if (e instanceof TaskNumberException) {
            System.out.println(":( Oops! Please enter a valid task number!");
            System.out.println("You currently have " + tasks.getTasksLength() + " tasks.");
        } else if (e instanceof TaskTypeException) {
            System.out.println(":( Oops! You can't do that to this type of tasks!");
        } else if (e instanceof EmptyFieldException) {
            System.out.println(":( Oops! One or more fields in the command is missing!");
        } else if (e instanceof DeadlineFormatException) {
            System.out.println(":( Oops! That's not the right way to set a deadline!");
            System.out.println("Please use this format: \"deadline <description> /by <time>\"");
        } else if (e instanceof EventFormatException) {
            System.out.println(":( Oops! That's not the right way to set an event!");
            System.out.println("Please use this format: \"event <description> /at <time>\"");
        } else {
            System.out.println(":( Oops! An unknown error has occurred!");
            e.printStackTrace();
        }
    }
}
