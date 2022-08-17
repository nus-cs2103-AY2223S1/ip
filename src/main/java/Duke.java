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
    private static void display(Commands command, String... message) {
        System.out.println(Messages.LINE_SEPARATION);
        switch (command) {
            case EXIT:
                System.out.println(Messages.EXIT);
                break;
            case SHOW_LIST:
                break;
            case GREET:
                System.out.println(Messages.GREET);
                System.out.println(Messages.LOGO);
                break;
            case ADD_TASK:
                break;
            case ADD_TODO:
                System.out.println(Messages.ADD_TODO);
                break;
            case ADD_EVENT:
                System.out.println(Messages.ADD_EVENT);
                break;
            case ADD_DEADLINE:
                System.out.println(Messages.ADD_DEADLINE);
                break;
            case MARK_DONE:
                System.out.println(Messages.MARK_DONE);
                break;
            case MARK_UNDONE:
                System.out.println(Messages.MARK_UNDONE);
                break;
            case DELETE:
                System.out.println(Messages.DELETE);
            default: // Every other command and invalid commands
                break;
        }

        for (String msg : message) {
            System.out.println(msg);
        }
        System.out.println(Messages.LINE_SEPARATION);
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

    /**
     * This retrieves the user input
     * 
     * @param input The user input to retrieve from
     * @param type  The type of input we are retrieving
     * @return The argument from the
     */
    private static String[] retrieve_arguments(String input, Commands cmd) {
        String[] inputs = input.split("./..."); // remove the /by
        switch (cmd) {
            case GET_COMMAND:
                String[] output = { input.split(" ")[0] };
                return output;
            case ADD_TODO:
                // TODO: Remove the hard-coded substring index
                inputs[0] = inputs[0].substring(5); // user description
                return inputs; // (descrpition)
            case ADD_EVENT:
                return inputs; // (descrpition, date)
            case ADD_DEADLINE:
                return inputs; // (description, date)
            case MARK_DONE:
                String[] indx = { input.split(" ")[1] };
                return indx;
            case DELETE:
                String[] ind = { input.split(" ")[1] };
                return ind;
            default: // No match or Invalid command
                String[] empty = { "" };
                return empty;
        }
    }

    public static void main(String[] args) throws IOException, DukeException {
        // Setting up to read command line inputs
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Greet the user
        display(Commands.GREET, "");
        // Continue to read inputs until the exit command is entered
        String input = reader.readLine();
        while (!input.equals(Commands.EXIT.toString())) {
            String cmd = retrieve_arguments(input, Commands.GET_COMMAND)[0];

            // Show task description in list
            if (cmd.equals(Commands.SHOW_LIST.toString())) {
                display(Commands.SHOW_LIST, tasks.toString());

            } else if (cmd.equals(Commands.MARK_DONE.toString())) { // Mark task as done
                try {
                    // Retrieve index from input
                    int indx = retrieve_arguments(input, Commands.MARK_DONE)[0].charAt(0) - '1';
                    Task current_task = tasks.get(indx);
                    current_task.toggleComplete();
                    // Display the marked message
                    if (current_task.isDone()) {
                        display(Commands.MARK_DONE, current_task.toString());
                    } else {
                        display(Commands.MARK_UNDONE, current_task.toString());
                    }
                } catch (IndexOutOfBoundsException e) {
                    display(Commands.ERROR, Messages.ERROR_INVALID_INDEX.toString());
                }

            } else if (cmd.equals(Commands.DELETE.toString())) { // delete task
                try {
                    int indx = retrieve_arguments(input, Commands.DELETE)[0].charAt(0) - '1';
                    Task deleted = tasks.removeTask(indx);
                    display(Commands.DELETE, deleted.toString());
                } catch (IndexOutOfBoundsException e) {
                    display(Commands.ERROR, Messages.ERROR_INVALID_INDEX.toString());
                }
            } else if (cmd.equals(Commands.ADD_TODO.toString())) { // Add todo
                // Retrieve description and date from input
                try {
                    String desc = retrieve_arguments(input, Commands.ADD_TODO)[0];
                    Todo current_todo = new Todo(desc);
                    tasks.addTask(current_todo);
                    display(Commands.ADD_TODO, current_todo.toString());
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    display(Commands.ERROR, Messages.ERROR_MISSING_PARAMETERS.toString());
                }

            } else if (cmd.equals(Commands.ADD_EVENT.toString())) {
                // Retrieve description and date from input
                try {
                    String desc = retrieve_arguments(input, Commands.ADD_EVENT)[0];
                    String date = retrieve_arguments(input, Commands.ADD_EVENT)[1];
                    Event current_event = new Event(desc, date);
                    tasks.addTask(current_event);
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    display(Commands.ERROR, Messages.ERROR_MISSING_PARAMETERS.toString());
                }

            } else if (cmd.equals(Commands.ADD_DEADLINE.toString())) {
                // Retrieve description and date from input
                try {
                    String desc = retrieve_arguments(input, Commands.ADD_DEADLINE)[0];
                    String date = retrieve_arguments(input, Commands.ADD_DEADLINE)[1];
                    Deadline current_deadline = new Deadline(desc, date);
                    tasks.addTask(current_deadline);
                    display(Commands.ADD_DEADLINE, current_deadline.getDescription());
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    display(Commands.ERROR, Messages.ERROR_MISSING_PARAMETERS.toString());
                }

            } else { // Invalid command
                display(Commands.ERROR, Messages.ERROR_INVALID_COMMAND.toString());
            }
            // Reads next input
            input = reader.readLine();
        }
        // Exit the bot
        display(Commands.EXIT, "");
    }
}
