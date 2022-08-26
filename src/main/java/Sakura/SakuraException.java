package Sakura;

/**
 * Throws custom exception commands if something goes wrong when processing user input.
 */
public class SakuraException {
    /**
     *  Renders an error message that the user input format for marking is invalid.
     */
    protected static void invalidMark() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR Mark Command written incorrectly!! ✿\n");
    }

    /**
     * Renders an error message that user did not specify the type of task
     */
    protected static void genericTask() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR What kind of task do you mean!! ✿\n");
    }

    /**
     * Renders an error message that there is no such task in the list of tasks.
     */
    protected static void noSuchTask() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR There is no such thing!! ✿\n");
    }

    /**
     * Renders an error message that user input format for deadline is invalid.
     */
    protected static void invalidDeadline() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR That is NOT a valid deadline!! ✿\n");
    }

    /**
     * Renders an error message that user input format for event is invalid.
     */
    protected static void invalidEvent() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR That is NOT a valid event!! ✿\n");
    }

    /**
     * Renders an error message that the user input has been written incorrectly.
     */
    protected static void invalidCommand() {
        System.out.println("\t(ﾐ`⋏´ﾐ) BOOOOO Can you even write a proper command?? ✿\n");
    }

    /**
     * Renders an error message that user input format for todo is invalid.
     */
    protected static void invalidTodo() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR Todo Command written incorrectly!! ✿\n");
    }

    /**
     * Renders an error message that the application has issues loading the database.
     */
    protected static void databaseError() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR There is something wrong with the database!! ✿\n");
    }

    /**
     * Renders an error message that the application has issues saving the data.
     */
    protected static void saveError() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR I cannot save this data!! ✿\n");
    }

    /**
     * Renders an error message that the user input for date is in the incorrect format.
     */
    protected static void dateError() {
        System.out.println("\t(ﾐ`⋏´ﾐ) RAWRRR Please enter date in the format yyyy-MM-dd HHmm!! ✿\\n\"");
    }
}
