package duke;

import java.util.Optional;
import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.Commands;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/*
 * Parser class reads the user input and parses it into valid commands.
 * It also checks for invalid commands and alerts the user to re-enter
 * a valid input.
 */
public class Parser {

    /*
     * Parses the user input and returns the corresponding command.
     * 
     * @return The command corresponding to the user input to execute.
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        try {
            String inputCommand = fullCommand.split(" ", 2)[0];
            String inputInstruction = fullCommand.split(" ", 2).length > 1 ? fullCommand.split(" ", 2)[1] : "";
            Commands command = parseInputCommand(inputCommand)
                    .orElseThrow(() -> new DukeException("Sorry, I don't understand you. Please try again."));
            switch (command) {
                case bye:
                    return new ByeCommand(inputInstruction);
                case list:
                    return new ListCommand(inputInstruction);
                case delete:
                    return new DeleteCommand(inputInstruction);
                case todo:
                    return new AddCommand(inputInstruction, new Todo(inputInstruction));
                case deadline:
                    return new AddCommand(inputInstruction, new Deadline(inputInstruction));
                case event:
                    return new AddCommand(inputInstruction, new Event(inputInstruction));
                case mark:
                    return new MarkCommand(inputInstruction);
                case unmark:
                    return new UnmarkCommand(inputInstruction);
                case find:
                    return new FindCommand(inputInstruction);
                default:
                    throw new DukeException("Sorry, I don't understand you. Please try again.");
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /*
     * Parses the user input command and returns the corresponding commands enum.
     * 
     * @return Optional<Commands> The corresponding commands enum.
     */
    public static Optional<Commands> parseInputCommand(String command) {
        for (Commands c : Commands.values()) {
            if (c.text.equals(command)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
}
