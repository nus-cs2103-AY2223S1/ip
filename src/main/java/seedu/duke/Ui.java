package seedu.duke;

public class Ui {
    private Parser parser;

    /**
     * A constructor that returns an instance of Ui.
     *
     * @param parser Interprets user input.
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Greets the user and calls the Parser to open a scanner and start
     * taking in user input.
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
     */
    public static String deleteText(String deleted, int size) {
        return "Noted. I've removed this task: \n"
                + deleted + "\n"
                + "Now you have " + size + " tasks in the list. \n";
    }

    /**
     * Tells the user what Task was added and how many Tasks are in the list.
     *
     * @param added Description of the Task added.
     * @param size The total number of tasks.
     */
    public static String addText(String added, int size) {
        return "Got it. I've added this task: \n"
                + added + "\n"
                + "Now you have " + size + " tasks in the list. \n";
    }

    /**
     * Informs the user that the task they tried to access does not exist.
     */
    public static String taskNotFoundText() {
        return "OOPS!!! That task doesn't exist. \n";
    }

    /**
     * Informs the user that the format of their command was incorrect.
     *
     * @param taskType The type of Task the user tried to create.
     */
    public static String emptyDescription(String taskType) {
        if (taskType.equals("Event")) {
            return "OOPS!!! The description of an Event cannot be empty. \n";
        } else {
            return "OOPS!!! The description of a " + taskType + " cannot be empty. \n";
        }
    }

    /**
     * Tells the user that Duke does not recognise the information in the save file.
     */
    public static String unknownElement() {
        return "OOPS!!! Unknown element in save file. \n";
    }

    /**
     * Tells the user that Duke was unable to add their task to the list.
     */
    public static String unableToAdd() {
        return "OOPS!!! Unable to add task. \n";
    }

    /**
     * Tells the user that their date format is incorrect.
     */
    public static String wrongDateFormat() {
        return "OOPS!!! The deadline must be in yyyy-MM-dd hh:mm AM/PM format. \n";
    }

    /**
     * Tells the user that Dukes was unable to find or create a save file.
     */
    public static String saveError() {
        return "OOPS!!! Unable to find/create save file. \n";
    }

    /**
     * Tells the user that their input was not recognised as a command.
     */
    public static String unknownCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-( \n";
    }

    /**
     * Bids farewell to the user and ends the program.
     */
    public static String bye() {
        return "Bye. Hope to see you again soon! \n";
    }
}
