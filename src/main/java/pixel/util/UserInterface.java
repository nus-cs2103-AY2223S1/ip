package pixel.util;

/**
 * Ui: deals with interactions with the user
 * Currently only contains messages to be displayed
 *
 * Needs more work atm, will require enums
 */
public class UserInterface {
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    // System.out.println("Hello from\n" + logo);
    // System.out.println("Hello from\n");

    public static final String introMessage = ("Hello! I'm Pixel \r\n" +
        "You can input the following commands \n" +
        " todo/ event/ deadline + <task description> + /by or /at + <due> \n" +
        " ***date format for due has to be in <yyyy-MM-dd(SPACE)HHmm(24h)> format \n" +
        " list -- lists out all the tasks \n" +
        " mark <index of task in the list> -- to mark as done \n" +
        " unmark <index of task in the list> -- to mark as not done \n" +
        " delete <index of task in the list> -- to delete that particular task \n" +
        " end -- leaves the chatbot \n" +
        "   Your input: ");

    public static final String goodbyeMessage = "Bye. Hope to see you again soon!";

}
