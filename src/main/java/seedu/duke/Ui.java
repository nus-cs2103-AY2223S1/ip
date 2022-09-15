package seedu.duke;

public class Ui {

    /**
     * Greets the user.
     *
     * @return A greeting to be displayed to the user.
     */
    public static String introduction() {
        String intro = "Hello! I'm Duke \n" + "What can I do for you?";

        return intro;
    }


    /**
     * Informs the user what task has been deleted and how many tasks are left.
     *
     * @param deleted The deleted task.
     * @param size The number of tasks remaining.
     * @return A response to be displayed to the user.
     */
    public static String deleteText(String deleted, int size) {
        return "Noted. I've removed this task: \n"
                + deleted + "\n"
                + "Now you have " + size + " task(s) in the list. \n";
    }

    /**
     * Tells the user what Task was added and how many Tasks are in the list.
     *
     * @param added Description of the Task added.
     * @param size The total number of tasks.
     * @return A response to be displayed to the user.
     */
    public static String addText(String added, int size) {
        return "Got it. I've added this task: \n"
                + added + "\n"
                + "Now you have " + size + " task(s) in the list. \n";
    }

    /**
     * Informs the user that the task they tried to access does not exist.
     *
     * @return A response to be displayed to the user.
     */
    public static String taskNotFoundText() {
        return "OOPS!!! That task doesn't exist. \n";
    }

    /**
     * Informs the user that the description field of their command cannot be empty.
     *
     * @param taskType The type of Task the user tried to create.
     * @return A response to be displayed to the user.
     */
    public static String emptyDescription(String taskType) {
        if (taskType.equals("Event")) {
            return "OOPS!!! The description of an Event cannot be empty. \n";
        } else {
            return "OOPS!!! The description of a " + taskType + " cannot be empty. \n";
        }
    }

    /**
     * Informs the user that there is an empty field in their command.
     *
     * @return A response to be displayed to the user.
     */
    public static String emptyField() {
        return "OOPS!!! all fields of your command must be filled. \n";
    }

    /**
     * Tells the user that Duke was unable to add their task to the list.
     *
     * @return A response to be displayed to the user.
     */
    public static String unableToAdd() {
        return "OOPS!!! Unable to add task. \n";
    }

    /**
     * Tells the user that their date format is incorrect.
     *
     * @return A response to be displayed to the user.
     */
    public static String wrongDateFormat() {
        return "OOPS!!! The deadline must be in yyyy-MM-dd hh:mm AM/PM format. \n";
    }

    /**
     * Tells the user that Dukes was unable to find or create a save file.
     *
     * @return A response to be displayed to the user.
     */
    public static String saveFileError() {
        return "OOPS!!! Unable to find/create save file. \n";
    }

    /**
     * Tells the user that their input was not recognised as a command.
     *
     * @return A response to be displayed to the user.
     */
    public static String unknownCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-( \n";
    }

    /**
     * Bids farewell to the user.
     *
     * @return A response to be displayed to the user.
     */
    public static String bye() {
        return "Bye. Hope to see you again soon! \n";
    }
}
