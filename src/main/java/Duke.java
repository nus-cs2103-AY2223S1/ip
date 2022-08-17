/**
 * Duke is a personal assistant chatbot.
 * It helps a person to keep track of various things.
 *
 * @author Rexong
 */
public class Duke {
    private static final String INTRODUCE_MESSAGE = "Hello! I'm Duke";
    private static final String ASK_MESSAGE = "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private TasksHandler tasksHandler;

    public Duke() {
        tasksHandler = new TasksHandler();
    }

    public void addTask(String command) {
        Task task = new Task(command);
        tasksHandler.addTaskProcess(task);
    }

    public void printTasks() {
        tasksHandler.printAllTasks();
    }

    /**
     * Print both introduce and ask messages.
     */
    public void printGreetMessage() {
        Console.log(INTRODUCE_MESSAGE);
        Console.log(ASK_MESSAGE);
    }

    /**
     * Print exit message.
     */
    public void printExitMessage() {
        Console.log(EXIT_MESSAGE);
    }

    /**
     * Echos commands entered by user.
     *
     * @param message Message entered by the user to be echoed
     */
    public void echoMessage(String message) {
        Console.log(message);
    }

}



























