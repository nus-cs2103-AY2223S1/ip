/**
 * This is Duke, he replies to messages.
 * 
 * @author Pei Cheng Yi A0229823Y
 */

// Reading command line inputs
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import enums.*;
import lists.*;
import exceptions.*;
import entities.*;
import utils.*;

public class Duke {
    /* Stores tasks from user */
    private static TaskList tasks = new TaskList();

    public static void main(String[] args) throws IOException, DukeException {
        // Setting up to read command line inputs
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Initialise utility objects
        OutputLogger out = new OutputLogger();
        CommandDispatcher cmd_dispatch = new CommandDispatcher();

        // Greet the user
        out.display(Commands.GREET, "");
        // Continue to read inputs until the exit command is entered
        String input = reader.readLine();
        while (!input.equals(Commands.EXIT.toString())) {
            String cmd = cmd_dispatch.retrieve_arguments(input, Commands.GET_COMMAND)[0];

            // Show task description in list
            if (cmd.equals(Commands.SHOW_LIST.toString())) {
                out.display(Commands.SHOW_LIST, tasks.toString());

            } else if (cmd.equals(Commands.MARK_DONE.toString())) { // Mark task as done
                try {
                    // Retrieve index from input
                    int indx = cmd_dispatch.retrieve_arguments(input, Commands.MARK_DONE)[0].charAt(0) - '1';
                    Task current_task = tasks.get(indx);
                    current_task.toggleComplete();
                    // out.display the marked message
                    if (current_task.isDone()) {
                        out.display(Commands.MARK_DONE, current_task.toString());
                    } else {
                        out.display(Commands.MARK_UNDONE, current_task.toString());
                    }
                } catch (IndexOutOfBoundsException e) {
                    out.display(Commands.ERROR, Messages.ERROR_INVALID_INDEX.toString());
                }

            } else if (cmd.equals(Commands.DELETE.toString())) { // delete task
                try {
                    int indx = cmd_dispatch.retrieve_arguments(input, Commands.DELETE)[0].charAt(0) - '1';
                    Task deleted = tasks.removeTask(indx);
                    out.display(Commands.DELETE, deleted.toString());
                } catch (IndexOutOfBoundsException e) {
                    out.display(Commands.ERROR, Messages.ERROR_INVALID_INDEX.toString());
                }
            } else if (cmd.equals(Commands.ADD_TODO.toString())) { // Add todo
                // Retrieve description and date from input
                try {
                    String desc = cmd_dispatch.retrieve_arguments(input, Commands.ADD_TODO)[0];
                    Todo current_todo = new Todo(desc);
                    tasks.addTask(current_todo);
                    out.display(Commands.ADD_TODO, current_todo.toString());
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    out.display(Commands.ERROR, Messages.ERROR_MISSING_PARAMETERS.toString());
                }

            } else if (cmd.equals(Commands.ADD_EVENT.toString())) {
                // Retrieve description and date from input
                try {
                    String desc = cmd_dispatch.retrieve_arguments(input, Commands.ADD_EVENT)[0];
                    String date = cmd_dispatch.retrieve_arguments(input, Commands.ADD_EVENT)[1];
                    Event current_event = new Event(desc, date);
                    tasks.addTask(current_event);
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    out.display(Commands.ERROR, Messages.ERROR_MISSING_PARAMETERS.toString());
                }

            } else if (cmd.equals(Commands.ADD_DEADLINE.toString())) {
                // Retrieve description and date from input
                try {
                    String desc = cmd_dispatch.retrieve_arguments(input, Commands.ADD_DEADLINE)[0];
                    String date = cmd_dispatch.retrieve_arguments(input, Commands.ADD_DEADLINE)[1];
                    Deadline current_deadline = new Deadline(desc, date);
                    tasks.addTask(current_deadline);
                    out.display(Commands.ADD_DEADLINE, current_deadline.getDescription());
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    out.display(Commands.ERROR, Messages.ERROR_MISSING_PARAMETERS.toString());
                }

            } else { // Invalid command
                out.display(Commands.ERROR, Messages.ERROR_INVALID_COMMAND.toString());
            }
            // Reads next input
            input = reader.readLine();
        }
        // Exit the bot
        out.display(Commands.EXIT, "");
    }
}
