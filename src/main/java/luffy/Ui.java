package luffy;

/**
 * Ui class to handle printing to console.
 *
 * @author Silas Tay (A0233425M)
 */
public class Ui {
    /**
     * Prints Divider line.
     */
    public void printDivider() {
        System.out.println("------------------------------------------------------");
    }

    /**
     * Prints Welcome message.
     */
    public void printWelcome() {
        String logo = "██╗░░░░░██╗░░░██╗███████╗███████╗██╗░░░██╗\n"
                + "██║░░░░░██║░░░██║██╔════╝██╔════╝╚██╗░██╔╝\n"
                + "██║░░░░░██║░░░██║█████╗░░█████╗░░░╚████╔╝░\n"
                + "██║░░░░░██║░░░██║██╔══╝░░██╔══╝░░░░╚██╔╝░░\n"
                + "███████╗╚██████╔╝██║░░░░░██║░░░░░░░░██║░░░\n"
                + "╚══════╝░╚═════╝░╚═╝░░░░░╚═╝░░░░░░░░╚═╝░░░";
        printDivider();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Prints Loading Error.
     */
    public void showLoadingError() {
        System.out.println("☹ OOPS!!! Something went wrong loading the save file!");
    }

    /**
     * Prints given Error Message with prefix.
     *
     * @param errorMessage Error message to print
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println("☹ OOPS!!! " + errorMessage);
    }

    /**
     * Prints given message.
     *
     * @param message Message to print
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints current size of TaskList.
     *
     * @param tasks TaskList to be printed
     */
    public void printTaskListStatus(TaskList tasks) {
        if (tasks.getSize() > 1) {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.");
        }
    }
}
