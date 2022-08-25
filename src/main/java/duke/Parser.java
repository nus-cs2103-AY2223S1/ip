package duke;

/**
 * Parser is used to parse the user's input and return specific phrases.
 *
 * @author Sean Lam
 */
public class Parser {
    //example input to parse: deadline taskToDo /by 2022-02-03 18:00-19:00

    /**
     * Returns command to execute.
     *
     * @param userInput user input
     * @return command
     */
    public static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Returns task's description.
     *
     * @param userInput user input
     * @return description
     */
    public static String getDescription(String userInput) {
        return userInput.split(" ", 2)[1].split(" /")[0];
    }

    /**
     * Returns date of deadline.
     *
     * @param userInput user input
     * @return date
     */
    public static String getDate(String userInput) {
        return userInput.split(" /")[1].split(" ")[1];
    }

    /**
     * Returns start time.
     *
     * @param userInput user input
     * @return start time
     */
    public static String getFrom(String userInput) {
        return userInput.split(" /")[1].split(" ")[2].split("-")[0];
    }

    /**
     * Returns end time.
     *
     * @param userInput user input
     * @return end time
     */
    public static String getTo(String userInput) {
        return userInput.split(" /")[1].split(" ")[2].split("-")[1];
    }

    /**
     * Returns index of task.
     *
     * @param userInput user input
     * @return index
     */
    public static int getIndex(String userInput) {
        return Integer.parseInt(getDescription(userInput)) - 1;
    }

    /**
     * Checks if user input a valid description
     *
     * @param userInput user input
     * @return Whether the description is valid
     */
    public static boolean isValidDescription(String userInput) {
        return userInput.split(" ").length > 1;
    }

    /**
     * Checks if user input a valid DateTime
     *
     * @param userInput user input
     * @return Whether the DateTime is valid
     */
    public static boolean isValidDateTime(String userInput) {
        return userInput.split("/")[1].split(" ").length > 1;
    }
}
