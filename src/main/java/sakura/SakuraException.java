package sakura;

/**
 * Throws custom exception commands if something goes wrong when processing user input.
 */
public class SakuraException extends Exception{
    /**
     *  Renders an error message that the user input format for marking is invalid.
     */
    public static String invalidMark() {
        return "\t(-_-) RAWRRR Mark Command written incorrectly!! \n";
    }

    /**
     * Renders an error message that user did not specify the type of task
     */
    public static String genericTask() {
        return "\t(-_-) RAWRRR What kind of task do you mean!! \n";
    }

    /**
     * Renders an error message that there is no such task in the list of tasks.
     */
    public static String noSuchTask() {
        return "\t(-_-) RAWRRR There is no such thing!! \n";
    }

    /**
     * Renders an error message that user input format for deadline is invalid.
     */
    public static String invalidDeadline() {
        return "\t(-_-) RAWRRR That is NOT a valid deadline!! \n";
    }

    /**
     * Renders an error message that user input format for event is invalid.
     */
    public static String invalidEvent() {
        return "\t(-_-) RAWRRR That is NOT a valid event!! \n";
    }

    /**
     * Renders an error message that the user input has been written incorrectly.
     */
    public static String invalidCommand() {
        return "\t(-_-) BOOOOO Can you even write a proper command?? \n";
    }

    /**
     * Renders an error message that user input format for todo is invalid.
     */
    public static String invalidTodo() {
        return "\t(-_-) RAWRRR Todo Command written incorrectly!! \n";
    }

    /**
     * Renders an error message that the application has issues loading the database.
     */
    public static String databaseError() {
        return "\t(-_-) RAWRRR There is something wrong with the database!! \n";
    }

    /**
     * Renders an error message that the application has issues saving the data.
     */
    public static String saveError() {
        return "\t(-_-) RAWRRR I cannot save this data!! \n";
    }

    /**
     * Renders an error message that the user input for date is in the incorrect format.
     */
    public static String dateError() {
        return "\t(-_-) RAWRRR Please enter date in the format yyyy-MM-dd HHmm!! \n";
    }
}
