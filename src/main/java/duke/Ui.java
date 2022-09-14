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
        System.out.println("How may I help you today, your Majesty?");
    }

    public void goodbye() {
        System.out.println("Glad to be of service, your Majesty.");
        isActive = false;
    }

    public void printSuccessfulClear() {
        System.out.println("Your duty list has been erased.");
    }

    public void printSuccessfulMark() {
        System.out.println("Excellent work, my Lord. You have completed the following duty:");
    }

    public void printSuccessfulUnmark() {
        System.out.println("Alas, there seems to be more work to be done for this duty:");
    }

    public void printSuccessfulAdd() {
        System.out.println("Understood, my Lord. I have added this duty:");
    }

    public void printSuccessfulDelete() {
        System.out.println("As you wish my Lord, I have removed this duty:");
    }

    public void printSuccessfulUpdate() {
        System.out.println("Right away my Lord, here is the amended duty:");
    }

    public void printNoOfTasks(TaskList ts) {
        System.out.println("You now have " + ts.getTasksLength() + " duties in your list.");
    }

    public void printTaskList(TaskList ts) {
        if (ts.getTasksLength() == 0) {
            System.out.println("You do not currently have any duties, your Majesty.");
        } else {
            System.out.println("Here are the duties in your list:");
            ts.printAllTasks();
        }
    }

    public void printTask(Task t) {
        System.out.println(t.toString());
    }

    public void printFoundResults(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("My apologies, I did not find any duties that match.");
        } else {
            System.out.println("Here are the duties in your list that matched:");
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
            System.out.println("Apologies my Lord, I'm having trouble writing/reading duty list.");
        } else if (e instanceof InvalidCommandException) {
            System.out.println("Sorry my Lord, I do not understand what you mean by " + e);
        } else if (e instanceof TaskNumberException) {
            System.out.println("Your Majesty, pray give me a valid duty number.");
            System.out.println("You currently have " + tasks.getTasksLength() + " duties.");
        } else if (e instanceof TaskTypeException) {
            System.out.println("I'm sorry your Majesty, I cannot do that with this type of duty.");
        } else if (e instanceof EmptyFieldException) {
            System.out.println("My Lord, pray give me more details in the command.");
        } else if (e instanceof DeadlineFormatException) {
            System.out.println("My Lord, it appears you are trying to set a Deadline.");
            System.out.println("Please use this format: \"deadline <description> /by <time>\"");
        } else if (e instanceof EventFormatException) {
            System.out.println("My Lord, it appears you are trying to set an Event.");
            System.out.println("Please use this format: \"event <description> /at <time>\"");
        } else {
            System.out.println("Woe! A strange terrible fate has befallen us!");
            e.printStackTrace();
        }
    }
}
