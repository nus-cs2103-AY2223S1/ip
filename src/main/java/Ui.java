import java.util.ArrayList;

/**
 * Class that represents the Ui, used to print
 * statements for the bot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Ui {
    /** String that represents a line. */
    protected String line = "____________________________________________________________";

    /**
     * Greets the user.
     */
    public void greet() {
        String greetings = "\nHello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(line + greetings + line);
    }

    /**
     * Echoes the user input.
     *
     * @param text The text to be echoed by the bot.
     */
    public void echo(String text) {
        System.out.println(line + "\n" + text + "\n" + line);
    }

    /**
     * Prints a message when the user adds a task to the bot.
     *
     * @param task The task that is added.
     * @param amountOfTasks The total amount of tasks that has been added by the user.
     */
    public void addTask(Task task, int amountOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + amountOfTasks + " tasks in the list.");
        System.out.println(line);

    }

    /**
     * Prints the list of tasks added by the user.
     *
     * @param taskList The list of tasks to be printed.
     */
    public void list(ArrayList<Task> taskList) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        int count = 1;

        for (Task task : taskList) {
            String taskName = task.toString();
            System.out.println(count + "." + taskName);
            count++;
        }

        System.out.println(line);
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param task The task that is marked as undone.
     */
    public void mark(Task task){
        String taskName = task.toString();

        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskName);
        System.out.println(line);
    }

    /**
     * Prints a message when a task is marked as undone.
     *
     * @param task The task that is marked as undone.
     */
    public void unmark(Task task){
        String taskName = task.toString();

        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskName);
        System.out.println(line);
    }

    /**
     * Prints a message when the bot exits.
     */
    public void exit(){
        String exitLine = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + exitLine + "\n" + line);
    }

    /**
     * Prints a message when a user inputs a command that does not exist.
     */
    public void commandDoesNotExist(){
        System.out.println(line);
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(line);
    }

    /**
     * Prints a message when a user deletes their tasks.
     *
     * @param task The task that is to be deleted.
     * @param amountOfTasks The total amount of tasks that the user has left.
     */
    public void delete(Task task, int amountOfTasks) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + amountOfTasks + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Prints an error message.
     *
     * @param e The exception that is to be printed.
     */
    public void errorMessage(DukeException e) {
        System.out.println(line);
        System.out.println(e.toString());
        System.out.println(line);
    }
}
