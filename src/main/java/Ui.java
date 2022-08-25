public class Ui {

    public static void greet() {
        System.out.println("Greetings Human! I am BetaGo, your personal robot assistant!\nHow may I assist you today?\n");
    }

    public static void goodbye() {
        System.out.println("Goodbye Human. Till next time.\n");
    }

    public static void printLoadFileError() {
        System.out.println("The data.txt file has an invalid entry. Delete the file and try again.\n");
    }

    public static void printFileNotFoundError() {
        System.out.println("There is an error in detecting the data file.\n");
    }

    public static void printInputOutputError() {
        System.out.println("Unable to create new data file.\n");
    }

    public static void printEmptyList() {
        System.out.println("You currently have no tasks in your list!\n");
    }
    public static void printInvalidMarkerError() {
        System.out.println("Please indicate a valid task number!\n");
    }
    public static void printInvalidCommands() {
        System.out.println("Apologies Human. I do not understand that command.\n");
    }
    public static void printInvalidTodoDescriptionError() {
        System.out.println("Please indicate a valid description for your Todo task!\n" +
                "Do enter the command in this format: 'todo (description)'\n");
    }
    public static void printInvalidDeadlineDescriptionError() {
        System.out.println("Please indicate a valid description and due date for your Deadline task!\n" +
                "Do enter the command in this format: 'deadline (description) /by (date) (time)'\n" +
                "Please enter the date in one of the following format: yyyy-MM-dd, dd-MMM-yyyy, dd/MM/yyyy\n");
    }
    public static void printInvalidEventDescriptionError() {
        System.out.println("Please indicate a valid description and location for your Event task!\n" +
                "Do enter the command in this format: 'deadline (description) /at (location)'\n");
    }
    public static void printSaveError() {
        System.out.println("There is an error when saving list to data file.\n");

    }
}
