package Duke.parser;

import Duke.commands.*;
import Duke.exceptions.DukeException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        //Fetch and display taskList
        if (input.equals("list")) {
            return new PrintListCommand();
        }

        // Mark a task
        if (input.startsWith("mark")) {
            return new MarkTaskCommand(input);
        }

        // Unmark a task
        if (input.startsWith("unmark")) {
            return new UnmarkTaskCommand(input);
        }

        // Delete a task
        if (input.startsWith("delete")) {
            return new DeleteTaskCommand(input);
        }

        // Add todo to taskList
        if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        }

        // Add deadline to taskList
        if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        }

        // Add event to taskList
        if (input.startsWith("event")) {
            return new AddEventCommand(input);
        }

        // Exits program
        if (input.startsWith("exit")) {
            return new ExitCommand();
        }
        return new IncorrectCommand();
    }
}


