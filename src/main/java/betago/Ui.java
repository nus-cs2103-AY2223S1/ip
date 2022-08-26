package betago;

/**
 * Ui class that prints statements for the user.
 */
public class Ui {

    /**
     * Prints a greeting message for the user.
     */
    public static void greet() {
        System.out.println("Greetings Human! I am BetaGo, your personal robot assistant!\nHow may I assist you today?\n");
    }

    /**
     * Prints a goodbye message for the user.
     */
    public static void goodbye() {
        System.out.println("Goodbye Human. Till next time.\n");
    }

    /**
     * Prints a load file error message where the data.txt file consist of an invalid entry.
     */
    public static void printLoadFileError() {
        System.out.println("The data.txt file has an invalid entry. Delete the file and try again.\n");
    }

    /**
     * Prints a file not found error message where there is an error in detecting the data file.
     */
    public static void printFileNotFoundError() {
        System.out.println("There is an error in detecting the data file.\n");
    }

    /**
     * Prints an input/output error message where there is an error in creating new data file.
     */
    public static void printInputOutputError() {
        System.out.println("Unable to create new data file.\n");
    }

    /**
     * Prints an invalid marker error message where the task number input from the user is invalid.
     */
    public static void printInvalidMarkerError() {
        System.out.println("Please indicate a valid task number!\n");
    }

    /**
     * Prints an empty list message where there are no tasks in the current list.
     */
    public static void printEmptyList() {
        System.out.println("You currently have no tasks in your list!\n");
    }

    /**
     * Prints an invalid command message where the program cannot understand the user input.
     */
    public static void printInvalidCommands() {
        System.out.println("Apologies Human. I do not understand that command.\n");
    }

    /**
     * Prints an invalid todo description message where the user did not input a description for the todo task.
     */
    public static void printInvalidTodoDescriptionError() {
        System.out.println("Please indicate a valid description for your Todo task!\n" +
                "Do enter the command in this format: 'todo (description)'\n");
    }

    /**
     * Prints an invalid deadline description message where the user did not input a
     * description or valid due date in the correct format for the deadline task.
     */
    public static void printInvalidDeadlineDescriptionError() {
        System.out.println("Please indicate a valid description and due date for your Deadline task!\n" +
                "Do enter the command in this format: 'deadline (description) /by (date) (time)'\n" +
                "Please enter the date in one of the following format: yyyy-MM-dd, dd-MMM-yyyy, dd/MM/yyyy\n");
    }

    /**
     * Prints an invalid event description message where the user did not input a
     * description or valid location in the correct format for the event task.
     */
    public static void printInvalidEventDescriptionError() {
        System.out.println("Please indicate a valid description and location for your Event task!\n" +
                "Do enter the command in this format: 'deadline (description) /at (location)'\n");
    }

    /**
     * Prints a save error message where there was an error in saving the TaskList to the data file.
     */
    public static void printSaveError() {
        System.out.println("There is an error when saving list to data file.\n");

    }
}
