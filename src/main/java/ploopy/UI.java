package ploopy;

/** Interacts with the user between commands
 *
 */
public class UI {
    /** Name of Chatbot */
    private static final String TEXT_ART = "PLOOPY";
    /** Top frame for layout */
    private static final String TOP_WINDOW = "---------------------------------------------------";
    /** Bottom frame for layout */
    private static final String BOTTOM_WINDOW = "---------------------------------------------------";

    /** Greeting message */
    private static final String GREETING = "Hi, I'm Ploopy! Nice to meet you!\n\tWhats up?";
    /** Farewell message */
    private static final String FAREWELL = "Okay then, see ya later :)";

    /** Completed task message */
    private static final String COMPLETED_TASK = "Nice! You've completed this task. I'll mark it as done.";
    /** Incomplete task message */
    private static final String INCOMPLETE_TASK = "Alright this task has been marked as undone.";
    /* Added task message */
    private static final String ADDED_TASK = "I've added this task to your list.\n\tHere you go: ";

    /** No input message */
    private static final String NO_INPUT_MESSAGE = "You didn't say what I should do!";
    /** Empty command message */
    private static final String EMPTY_COMMAND_MESSAGE = "What should I do with the ";
    /** Nonsense input message */
    private static final String NONSENSE_INPUT_MESSAGE = "I can't do that right now, sorry";
    /** Storage file error message */
    private static final String IO_ERROR = "Something went wrong creating your files!";

    /**
     * Prints a greeting to the user.
     *
     */
    public void greeting() {
        System.out.println(messageFormatter(TEXT_ART));
        System.out.println(messageFormatter(GREETING));
    }

    /**
     * Prints a bye message to the user
     *
     */
    public void bye() {
        System.out.println(messageFormatter(FAREWELL));
    }

    /**
     * Asks the user for a command.
     *
     */
    public void promptUser() {
        System.out.println(messageFormatter("What do you wanna do to your list?"));
    }

    /**
     * Acknowledges the specific task has been added and
     * tells the user how many tasks are left.
     *
     * @param task Task that has been added.
     * @param totalTasks Number of tasks remaining after adding.
     */
    public void addTaskMessage(Task task, int totalTasks) {
        String message = ADDED_TASK + task.toString() + "\n\tYou have " + totalTasks + " tasks in your list.";
        System.out.println(messageFormatter(message));
    }

    /**
     * Tells the user that a certain task has been marked as done.
     *
     * @param task The task that has been completed.
     */
    public void markTaskMessage(Task task) {
        System.out.println(messageFormatter(COMPLETED_TASK + "\n\t" + " " + task));
    }

    /**
     * Tells the user that a certain task has been marked as undone.
     *
     * @param task The task that is marked incomplete.
     */
    public void unmarkTaskMessage(Task task) {
        System.out.println(messageFormatter(INCOMPLETE_TASK + "\n\t" + " " + task));
    }

    /**
     * Tells the user that a certain task has been deleted and
     * how many tasks are left.
     *
     * @param task The deleted task.
     * @param index The number of tasks remaining.
     */
    public void deleteTaskMessage(Task task, int index) {
        String message = "Deleted: " + task + "\n\tYou have "
                + index + " task(s) remaining.";
        System.out.println(messageFormatter(message));
    }

    public void createFilesMessage() {
        System.out.println(messageFormatter("Creating necessary files..."));
    }

    public void addingFilesMessage() {
        System.out.println(messageFormatter("Adding existing tasks..."));
    }

    /**
     * Prints the correct message to the user according
     * to the exception tag.
     *
     * @param type The type and data of the exception.
     */
    public void exceptionMessage(String type) {
        switch (type) {
            case "nonsense":
                System.out.println(messageFormatter(NONSENSE_INPUT_MESSAGE));
                break;
            case "blank":
                System.out.println(messageFormatter(NO_INPUT_MESSAGE));
                break;
            case "IO":
                System.out.println(messageFormatter(IO_ERROR));
            default:
                System.out.println(messageFormatter(EMPTY_COMMAND_MESSAGE + type));
        }
    }

    /**
     * Formats any message to match the layout of the console output.
     *
     * @param input Message to format.
     * @return Formatted Message.
     */

    private String messageFormatter(String input) {
        return TOP_WINDOW + "\n\t" + input + "\n" + BOTTOM_WINDOW;
    }

    public void showTopWindow() {
        System.out.println(TOP_WINDOW);
    }

    public void showBottomWindow() {
        System.out.println(BOTTOM_WINDOW);
    }

}
