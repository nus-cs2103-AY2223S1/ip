package wagwan;

/**
* WagwanUi is the user interface of Wagwan to return responses according to user inputs
*
* @author Linus Chui
*/
public class WagwanUi {

    public static final String INVALID_COMMAND = " is not a valid command my g. "
            + "valid commands are list, mark (number), unmark (number), todo (task), deadline (task) /by yyyy/mm/dd,"
            + "event (task) /at (time), delete (number), find (keyword), update (index) (new description)";
    public static final String INVALID_DESCRIPTION = "The command needs a description init";
    public static final String INVALID_INDEX = "This index aint no number.";
    public static final String INDEX_OUT_OF_RANGE = "This index is outta range brother";
    public static final String INVALID_EVENT = "The description/time of an event cannot be empty!";
    public static final String INVALID_DEADLINE = "The description/time of a deadline cannot be empty!";
    public static final String INVALID_DATE = "Listen man your deadline date must be of format yyyy-mm-dd";
    public static final String ADD_TASK_ERROR = "Error adding task, please try again";
    public static final String DELETE_TASK_ERROR = "Error deleting task, please try again";
    public static final String CLASS_CAST_ERROR = "Error occured when categorising task, please try again";
    public static final String FILE_NOT_FOUND_ERROR = "Error finding file, the file might have been "
        + "deleted from your computer.";
    public static final String FILE_CREATE_ERROR = "Duke has encountered an error while creating a file. "
        + "Please exit and try again";
    public static final String FILE_READ_ERROR = "Your file is corrupted, please delete the file /data/tasks.txt "
        + "from your computer and restart the program";
    private static final String START_MESSAGE = " wagwan my g mandem's making bare Ps init";
    private static final String END_MESSAGE = " in a bit, peace brotha";

    /**
     * Splits user input into a String array of length 2.
     *
     * @param input the user inpuut.
     * @return a String array consisting of the user input of maximum length 2.
     *      where index 0 is the command and index 1 is the input.
     */
    public String[] readCommand(String input) {
        assert input != "" : "Please input a command!";
        String[] command = input.split(" ", 2);
        return command;
    }

    /**
     * Prints a message from Duke.
     *
     * @param message the message to be printed out.
     */
    public static String sendMessage(String message) {
        return message;
    }

    /**
     * Prints Duke's welcome message when program starts.
     */
    public static String welcomeMessage() {
        return START_MESSAGE;
    }

    /**
     * Prints a loading error if Duke is unable to find a .txt file to load
     * in the user's device and informs the user that a new file will be created
     * to save the user's tasks.
     */
    public String showLoadingError() {
        return "File not found, creating new file in current directory";
    }

    /**
     * Prints Duke's goodbye message before closing program.
     */
    public String endMessage() {
        return END_MESSAGE;
    }
}
