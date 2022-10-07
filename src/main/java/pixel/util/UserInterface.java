package pixel.util;

/**
 * Ui: deals with interactions with the user
 * Currently only contains messages to be displayed
 *
 * Needs more work atm, will require enums
 */
public class UserInterface {

    public static final String GREETING_MESSAGE = ("Hello! I'm Pixel! \r\n");
    public static final String PROMPT_MESSAGE = (
        "You can input the following commands \n"
            + " todo/ event/ deadline + <task description> + /by or /at + <due/ location> \n"
            + " ***date format for due has to be in <yyyy-MM-dd(SPACE)HHmm(24h)> format \n"
            + " list -- lists out all the tasks \n"
            + " mark <index of task in the list> -- to mark as done \n"
            + " unmark <index of task in the list> -- to mark as not done \n"
            + " delete <index of task in the list> -- to delete that particular task \n"
            + " find <query> -- Find all tasks with description containing query \n"
            + " bye -- leaves the chatbot and closes the programme \n \n"
            + "   Your input: ");

    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String AFTER_VALID_INPUT = "You may enter a new task or command";
    public static final String AFTER_INVALID_INPUT = "Oops! Make sure the input follows the following format:";
    public static final String FILE_LOADED = "Loaded tasks from external file!";
    public static final String FILE_DOES_NOT_EXIST = "File does not exist! Pixel has created a new file for you";
    public static final String FILE_TASKS_INVALID = "Oops! Seems like some of the tasks in the file "
        + "are not in the right format, do check the formatting!";

    private String oldDukeLogo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

}
