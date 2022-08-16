/**
 * This is Duke, he replies to messages.
 * 
 * @author Pei Cheng Yi A0229823Y
 */

// for reading command line inputs
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    /* Stores tasks from user */
    private static TaskList tasks = new TaskList();

    /**
     * General display function to print messages to the console
     * 
     * @param command The command that is being exectued
     * @param message Any additional information that should be printed with the
     *                command
     */
    private static void display(Commands command, String message) {
        System.out.println(Messages.LINE_SEPARATION);
        switch (command) {
            case EXIT:
                System.out.println(Messages.EXIT);
                break;
            case SHOW_LIST:
                System.out.println(message);
                break;
            case GREET:
                System.out.println(Messages.GREET);
                System.out.println(Messages.LOGO);
                break;
            case ADD_TASK:
                System.out.println(message);
                break;
            case MARK_DONE:
                System.out.println(Messages.MARK_DONE);
                System.out.println(message);
                break;
            case MARK_UNDONE:
                System.out.println(Messages.MARK_UNDONE);
                System.out.println(message);
        }
        System.out.println(Messages.LINE_SEPARATION);
    }

    /**
     * Adds task to list
     * 
     * @param task Task description in String
     */
    private static void addTask(String task) {
        tasks.addTask(new Task(task));
    }

    /**
     * The echo function prints the input given to it
     * 
     * @param input
     * @return void
     */
    private static void echo(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) throws IOException {
        // Setting up to read command line inputs
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Greet the user
        display(Commands.GREET, "");
        // Continue to read inputs until the exit command is entered
        String input = reader.readLine();
        while (!input.equals(Commands.EXIT.command)) {
            String[] inputs = input.split(" ");

            // Show task description in list
            if (input.equals(Commands.SHOW_LIST.command)) {
                display(Commands.SHOW_LIST, tasks.toString());
                // Mark task as done
            } else if (inputs[0].equals(Commands.MARK_DONE.command)) {
                int indx = inputs[1].charAt(0) - '1';
                Task current_task = tasks.get(indx);
                current_task.toggleComplete();
                // Display the marked message
                if (current_task.isDone()) {
                    display(Commands.MARK_DONE, current_task.toString());
                } else {
                    display(Commands.MARK_UNDONE, current_task.toString());
                }
            } else {
                addTask(input);
                display(Commands.ADD_TASK, Messages.ADD_TASK + input);
            }
            // Reads next input
            input = reader.readLine();
        }
        // Exit the bot
        display(Commands.EXIT, "");
    }
}
