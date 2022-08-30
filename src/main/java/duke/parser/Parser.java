package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;

public class Parser {

    /**
     * Parses the input and directs Duke to execute the appropriate command.
     *
     * @param input input String from the user
     *
     * @return Command to be executed in Duke.run()
     *
     * @throws DukeException to handle inappropriate inputs
     */
    public static Command parse(String input) throws DukeException {
        //Fetch and display taskList
        if (input.equals("list")) return new PrintListCommand();

        // Mark a task
        if (input.startsWith("mark")) return new MarkTaskCommand(input);

        // Unmark a task
        if (input.startsWith("unmark")) return new UnmarkTaskCommand(input);

        // Deletes all tasks from the list
        if (input.equals("delete all")) return new DeleteAllCommand();

        // Delete a task
        if (input.startsWith("delete")) return new DeleteTaskCommand(input);

        // Add todo to taskList
        if (input.startsWith("todo")) return new AddTodoCommand(input);

        // Add deadline to taskList
        if (input.startsWith("deadline")) return new AddDeadlineCommand(input);

        // Add event to taskList
        if (input.startsWith("event")) return new AddEventCommand(input);

        // Exits program
        if (input.startsWith("exit")) return new ExitCommand();

        // In case of an unrecognised command
        return new IncorrectCommand();
    }
}


