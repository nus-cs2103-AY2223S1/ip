package sakura;

/**
 * Throws custom exception commands if something goes wrong when processing user input.
 */
public class SakuraException extends Exception{
    /**
     *  Renders an error message that the user input format for marking is invalid.
     */
    protected static String invalidMark() {
        return "\t(`0´) RAWRRR Mark Command written incorrectly!! ✿\n";
    }

    /**
     * Renders an error message that user did not specify the type of task
     */
    protected static String genericTask() {
        return "\t(`0´) RAWRRR What kind of task do you mean!! ✿\n";
    }

    /**
     * Renders an error message that there is no such task in the list of tasks.
     */
    protected static String noSuchTask() {
        return "\t(`0´) RAWRRR There is no such thing!! ✿\n";
    }

    /**
     * Renders an error message that user input format for deadline is invalid.
     */
    protected static String invalidDeadline() {
        return "\t(`0´) RAWRRR That is NOT a valid deadline!! ✿\n";
    }

    /**
     * Renders an error message that user input format for event is invalid.
     */
    protected static String invalidEvent() {
        return "\t(`0´) RAWRRR That is NOT a valid event!! ✿\n";
    }

    /**
     * Renders an error message that the user input has been written incorrectly.
     */
    protected static String invalidCommand() {
        return "\t(`0´) BOOOOO Can you even write a proper command?? ✿\n";
    }

    /**
     * Renders an error message that user input format for todo is invalid.
     */
    protected static String invalidTodo() {
        return "\t(`0´) RAWRRR Todo Command written incorrectly!! ✿\n";
    }

    /**
     * Renders an error message that the application has issues loading the database.
     */
    protected static String databaseError() {
        return "\t(`0´) RAWRRR There is something wrong with the database!! ✿\n";
    }

    /**
     * Renders an error message that the application has issues saving the data.
     */
    protected static String saveError() {
        return "\t(`0´) RAWRRR I cannot save this data!! ✿\n";
    }

    /**
     * Renders an error message that the user input for date is in the incorrect format.
     */
    protected static String dateError() {
        return "\t(`0´) RAWRRR Please enter date in the format yyyy-MM-dd HHmm!! ✿\n";
    }
}
