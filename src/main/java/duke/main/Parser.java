package duke.main;

import java.time.DateTimeException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventsCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * Represents a Parser that determines what kind of input was obtained
 * from the user and then decisively returns different commands
 *
 */
public class Parser {

    /**
     * parse method that reads the input that was given to it and decides which type of
     * to instantiate as an object and return it
     *
     * @param input
     * @return Command
     */
    public Command parse(String input) {
        String [] inputs = input.split(" ");
        String command = inputs[0];
        try {
            if (command.equals("list")) {
                return new ListCommand();
            } else if (command.equals("bye")) {
                return new ByeCommand();
            } else {
                String task = inputs[1];

                if (command.equals("mark")) {
                    return new MarkCommand(task);
                } else if (command.equals("unmark")) {
                    return new UnmarkCommand(task);
                } else if (command.equals("todo")) {
                    return new AddTodoCommand(input);
                } else if (command.equals("deadline")) {
                    return new AddDeadlineCommand(input);
                } else if (command.equals("event")) {
                    return new AddEventsCommand(input);
                } else if (command.equals("delete")) {
                    return new DeleteCommand(inputs[1]);
                } else if (command.equals("find")) {
                    return new FindCommand(input);
                } else {
                    return new ErrorCommand();
                }
            }
        } catch (DateTimeException e) {
            System.out.println("Error!");
        }
        return new ErrorCommand();
    }
}
