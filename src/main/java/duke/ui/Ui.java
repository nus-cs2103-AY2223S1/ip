package duke.ui;

import java.util.Scanner;

import duke.task.Task.TaskType;

/**
 * Encapsulates a user interface that interacts with the user.
 */
public class Ui {
    public static final String START = '\u2619' + " ";
    public static final String SAD_FACE = '\u2639' + " ";

    private Scanner sc = new Scanner(System.in);

    /**
     * Sends a message when information saved in Duke is not able
     * to be stored on the hard drive.
     */
    public void fileLoadError() {
        System.out.println(
                SAD_FACE + "hmm, i was not able to create a file to store your list.\n"
                + "  note that your list will not be saved if you quit and restart duke.Duke!"
        );
    }

    /**
     * Sends a welcome message upon starting up Duke.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(START + "hi, i'm Duke!\n  what would you like to do today?");
    }

    /**
     * Indicates to the user that the bot is ready to receive inputs.
     *
     * @return The user input.
     */
    public String requestInput() {
        System.out.print("> ");
        return sc.nextLine();
    }

    /**
     * Informs the user that they have entered an invalid argument for the
     * "mark" command.
     */
    public void invalidMarkInput() {
        System.out.println(SAD_FACE + "please enter an integer so i know which task to mark!");
    }

    /**
     * Informs the user that they have entered an invalid argument for the
     * "unmark" command.
     */
    public void invalidUnmarkInput() {
        System.out.println(SAD_FACE + "please enter an integer so i know which task to unmark!");
    }

    /**
     * Informs the user that they have entered invalid information for
     * the task to be added.
     *
     * @param t The type of the task for which information has been entered wrongly.
     */
    public void invalidTaskInput(TaskType t) {
        if (t == TaskType.ToDo) {
            System.out.println(SAD_FACE + "please tell me the name of the todo task.");
        } else if (t == TaskType.Deadline) {
            System.out.println(
                    SAD_FACE + "for tasks with deadlines, please tell me the name of the task, followed "
                            + "by '/by',\n  and then the date/time it needs to be completed by\n"
                            + "  in DD/MM/YYYY HHMM (time optional) format e.g. 27/02/2023 2359.");
        } else {
            System.out.println(
                    SAD_FACE + "for events, please tell me the name of the event, when it starts and when it ends.");
        }
    }

    /**
     * Informs the user when they have not specified a date for the
     * "search" command.
     */
    public void emptyDateInput() {
        System.out.println(
                SAD_FACE + "please enter the date you would like to search for in the format DD/MM/YYYY.");
    }

    /**
     * Informs the user when they have not entered the date in a valid format.
     */
    public void invalidDateInput() {
        System.out.println(SAD_FACE + "please enter a valid date in the format DD/MM/YYYY.");
    }

    /**
     * Informs the user when they have not entered the date and time
     * in a valid format.
     */
    public void invalidDateTimeInput() {
        System.out.println(
                SAD_FACE + "please input a valid date and time in the format DD/MM/YYYY HHMM (time optional)");
    }

    /**
     * Informs the user when they have not entered the dates and time of an event in a valid format.
     */
    public void invalidStartEndDateInput() {
        System.out.println(
                SAD_FACE + "please input valid starting and ending dates and times in the format\n"
                        + "  DD/MM/YYYY HHMM - DD/MM/YYYY HHMM (ending date optional)"
        );
    }

    /**
     * Informs the user that they have entered an invalid argument for the
     * "delete" command.
     */
    public void invalidDeleteInput() {
        System.out.println(SAD_FACE + "please enter an integer so i know which task to delete!");
    }

    /**
     * Sends when the user enters any commands not recognised by Duke.
     */
    public void invalidInput() {
        System.out.println(SAD_FACE + "sorry, i don't know what that means :(");
    }

    /**
     * Informs the user when they have not specified any search terms for the
     * "find" command.
     */
    public void emptyFindInput() {
        System.out.println(SAD_FACE + "please enter the keywords you would like to search for.");
    }

    /**
     * Sends a goodbye message in the event that the task list created by Duke could not be
     * saved to the hard drive.
     */
    public void saveFail() {
        System.out.println(
                START + "bye! i was unable to save your list to the hard drive, but i hope to see you again soon! :)");
    }

    /**
     * Sends a goodbye message upon exiting Duke.
     */
    public void goodbye() {
        System.out.println(START + "bye! i hope to see you again soon! :)");
    }

}

