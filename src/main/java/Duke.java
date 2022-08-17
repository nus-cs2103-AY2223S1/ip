import java.util.ArrayList;

/**
 * Duke is a personal assistant chatbot.
 * It helps a person to keep track of various things.
 *
 * @author Rexong
 */
public class Duke {
    //Messages from Duke
    private static final String INTRODUCE_MESSAGE = "Hello! I'm Duke";
    private static final String ASK_MESSAGE = "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private ArrayList<Task> tasks;

    /**
     * Constructor with task initialised as ArrayList
     */
    public Duke() {
        tasks = new ArrayList<>();
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

    /**
     * Add the new task to tasks.
     * if tasks is full, it returns nothing
     *
     * @param task task to be added to tasks
     */
    private void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Displays the newly added task to tasks.
     */
    private void addTaskMessage() {
        Console.log("added: " + tasks.get(tasks.size()-1));
    }

    /**
     * Combines both addTask and addTaskMessage into one process
     *
     * @param text new task to be added
     */
    public void addTaskProcess(String text) {
        Task task = new Task(text);
        addTask(task);
        addTaskMessage();
    }

    /**
     * Displays all task stored in tasks.
     */
    public void printAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Console.log(String.format("%d. %s", i + 1, tasks.get(i)));
        }
    }

}



























