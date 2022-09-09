package caca.ui;

/**
 * This class deals with interactions with the user.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Ui {

    /**
     * Greets user with CaCa logo and greeting.
     *
     * @return Greeting message.
     */
    public static String greet() {
        String greetMessage = "Hello (> O <)\n"
                + "Welcome to CaCa's world!\nWhat can I do for you?\n";
        return greetMessage;
    }

    /**
     * Says bye to user.
     *
     * @return Goodbye message.
     */
    public String bye() {
        String byeMessage = "Bye (T_T)\n"
                + "Hope to see you again soon!";
        return byeMessage;
    }

    /**
     * Displays a quick start to guide users with the commands in CaCa.
     *
     * @return Quick start message.
     */
    public static String showQuickStart() {
        String quickStartMessage = "*****A quick start guide to CaCa*****\n"
                + "help, todo, deadline, event, list, mark, unmark, delete, find, bye.";
        return quickStartMessage;
    }

    /**
     * Displays a detailed command list to guide users with all the commands in CaCa.
     *
     * @return A list of all the commands in detail.
     */
    public static String showDetailedGuide() {
        String detailedCommandsMessage = "*****Full command guide to CaCa*****\n"
                + "* help\n"
                + "* todo taskDescription \n"
                + "    e.g. todo borrow book\n"
                + "* deadline taskDescription /by dd/MM/yyyy HHmm\n"
                + "    e.g. deadline return book /by 01/09/2022 1200\n"
                + "* event taskDescription /at dd/MM/yyyy HHmm\n"
                + "    e.g. event project meeting /at 01/09/2022 1600\n"
                + "* list\n"
                + "* mark taskIndex\n"
                + "    e.g. mark 2\n"
                + "* unmark taskIndex\n"
                + "    e.g. unmark 2\n"
                + "* delete taskIndex\n"
                + "    e.g. delete 3\n"
                + "* find taskDescription\n"
                + "    e.g. find book\n"
                + "* bye\n";
        return detailedCommandsMessage;
    }

}
