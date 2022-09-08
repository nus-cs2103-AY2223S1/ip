package duke.ui;

import duke.task.Task.TaskType;

/**
 * Encapsulates a user interface that interacts with the user.
 */
public class Ui {
    public static final String START = '\u2619' + " ";
    public static final String SAD_FACE = '\u2639' + " ";

    /**
     * Sends a message when information saved in Duke is not able
     * to be stored on the hard drive.
     */
    public static void fileLoadError() {
        System.out.println(
                SAD_FACE + "hmm, i was not able to create a file to store your list.\n"
                + "  note that your list will not be saved if you quit and restart duke.Duke!"
        );
    }

    /**
     * Sends a welcome message.
     *
     * @return The welcome message.
     */
    public static String welcome() {
        return START + "hi, i'm Duke!\n   what would you like to do today?";
    }

    /**
     * Informs the user that they have entered an invalid argument for the
     * "mark" command.
     *
     * @return A string informing the user that they have entered an invalid argument.
     */
    public static String invalidMarkInput() {
        return SAD_FACE + "please enter an integer so i know which task to mark!";
    }

    /**
     * Informs the user that they have entered an invalid argument for the
     * "unmark" command.
     *
     * @return A string informing the user that they have entered an invalid argument.
     */
    public static String invalidUnmarkInput() {
        return SAD_FACE + "please enter an integer so i know which task to unmark!";
    }

    /**
     * Informs the user that they have entered invalid information for
     * the task to be added.
     *
     * @param t The type of the task for which information has been entered wrongly.
     * @return A string informing the user that they have entered invalid information.
     */
    public static String invalidTaskInput(TaskType t) {
        String reply;
        if (t == TaskType.ToDo) {
            reply = SAD_FACE + "please tell me the name of the todo task.";
        } else if (t == TaskType.Deadline) {
            reply = SAD_FACE + "for tasks with deadlines, please tell me the name of the task, followed "
                    + "by '/by',\n  and then the date/time it needs to be completed by\n"
                    + "  in DD/MM/YYYY HHMM (time optional) format e.g. 27/02/2023 2359.";
        } else {
            reply = SAD_FACE + "for events, please tell me the name of the event, when it starts and when it ends.";
        }
        return reply;
    }

    /**
     * Informs the user when they have not specified a date for the
     * "search" command.
     *
     * @return A string informing the user that they have not entered a date to search for.
     */
    public static String emptyDateInput() {
        return SAD_FACE + "please enter the date you would like to search for in the format DD/MM/YYYY.";
    }

    /**
     * Informs the user when they have not entered the date in a valid format.
     *
     * @return A string informing the user that they have entered the date in an invalid format.
     */
    public static String invalidDateInput() {
        return SAD_FACE + "please enter a valid date in the format DD/MM/YYYY.";
    }

    /**
     * Informs the user when they have not entered the date and time
     * in a valid format.
     *
     * @return A string informing the user that they have entered the date and time
     *     in an invalid format.
     */
    public static String invalidDateTimeInput() {
        return SAD_FACE + "please input a valid date and time in the format DD/MM/YYYY HHMM (time optional)";
    }

    /**
     * Informs the user when they have not entered the dates and time of an event in a valid format.
     *
     * @return A string informing the user that they have entered the date and time
     *     in an invalid format.
     */
    public static String invalidStartEndDateInput() {
        return SAD_FACE + "please input valid starting and ending dates and times in the format\n"
                + "  DD/MM/YYYY HHMM - DD/MM/YYYY HHMM (ending date optional)";
    }

    /**
     * Informs the user that they have entered an invalid argument for the
     * "delete" command.
     *
     * @return A string informing the user that they did not specify the task to delete..
     */
    public static String invalidDeleteInput() {
        return SAD_FACE + "please enter an integer so i know which task to delete!";
    }

    /**
     * Sends when the user enters any commands not recognised by Duke.
     *
     * @return A string informing the user whenever Duke does not understand the input.
     */
    public static String invalidInput() {
        return SAD_FACE + "sorry, i don't know what that means :(";
    }

    /**
     * Informs the user when they have not specified any search terms for the
     * "find" command.
     *
     * @return A string informing the user that they have not entered any keywords to search for.
     */
    public static String emptyFindInput() {
        return SAD_FACE + "please enter the keywords you would like to search for.";
    }

    /**
     * Sends a goodbye message in the event that the task list created by Duke could not be
     * saved to the hard drive.
     *
     * @return A string containing the goodbye message and informing the user that the task list
     *     could not be saved to the hard drive.
     */
    public static String saveFail() {
        return START + "bye! i was unable to save your list to the hard drive, but i hope to see you again soon! :)";
    }

    /**
     * Sends a goodbye message upon exiting Duke.
     *
     * @return The goodbye message.
     */
    public static String goodbye() {
        return START + "bye! i hope to see you again soon! :)";
    }

}

