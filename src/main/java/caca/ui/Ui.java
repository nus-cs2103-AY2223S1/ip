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
        return "Hello! I'm CaCa.\nWhat can I do for you?\n";
    }

    /**
     * Says bye to user.
     *
     * @return Goodbye message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a quick start to guide users with the commands in CaCa.
     *
     * @return Quick start message.
     */
    public static String showQuickStart() {
        String quickStartMessage = "*****A quick start guide to CaCa*****\n"
                + "todo, deadline, event, list, mark, unmark, delete, find, bye.";
        return quickStartMessage;
    }

    /**
     * Displays a detailed command list to guide users with all the commands in CaCa.
     *
     * @return A list of all the commands in detail.
     */
    public static String showDetailedGuide() {
        String detailedCommandsMessage = "*****A comprehensive guide to CaCa*****\n"
                + "* todo taskDescription \n"
                + "    e.g. todo borrow book\n"
                + "* deadline taskDescription /by dd/MM/yyyy HHmm\n"
                + "    e.g. deadline return book /by 01/09/2022 1200\n"
                + "* event taskDescription /at dd/MM/yyyy HHmm\n"
                + "    e.g. event project meeting /at 01/09/2022 1600\n"
                + "* list: displays a list of all your tasks.\n"
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
