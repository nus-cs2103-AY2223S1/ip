package rick;

import java.util.Optional;

import rick.commands.AddCommand;
import rick.commands.ByeCommand;
import rick.commands.Command;
import rick.commands.Commands;
import rick.commands.DeleteCommand;
import rick.commands.FindCommand;
import rick.commands.ListCommand;
import rick.commands.MarkCommand;
import rick.commands.UnmarkCommand;
import rick.tasks.Deadline;
import rick.tasks.Event;
import rick.tasks.Todo;

/**
 * Parser class reads the user input and parses it into valid commands.
 * It also checks for invalid commands and alerts the user to re-enter
 * a valid input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     * 
     * @return The command corresponding to the user input to execute.
     */
    public static Command parseCommand(String fullCommand) throws RickException {
        try {
            String inputCommand = fullCommand.split(" ", 2)[0];
            String inputInstruction = fullCommand.split(" ", 2).length > 1 ? fullCommand.split(" ", 2)[1] : "";
            Commands command = parseInputCommand(inputCommand)
                    .orElseThrow(() -> new RickException("Morty, I don't understand you! Please try again."));
            switch (command) {
                case bye:
                    return new ByeCommand(inputInstruction);
                case list:
                    return new ListCommand(inputInstruction);
                case delete:
                    return new DeleteCommand(inputInstruction);
                case todo:
                    if (Todo.isValidInput(inputInstruction)) {
                        return new AddCommand(inputInstruction, new Todo(inputInstruction));
                    }
                case deadline:
                    if (Deadline.isValidInput(inputInstruction)) {
                        return new AddCommand(inputInstruction, new Deadline(inputInstruction));
                    }
                case event:
                    if (Event.isValidInput(inputInstruction)) {
                        return new AddCommand(inputInstruction, new Event(inputInstruction));
                    }
                case mark:
                    return new MarkCommand(inputInstruction);
                case unmark:
                    return new UnmarkCommand(inputInstruction);
                case find:
                    return new FindCommand(inputInstruction);
                default:
                    throw new RickException("Morty, I don't understand you! Please try again.");
            }
        } catch (Exception e) {
            throw new RickException(e.getMessage());
        }
    }

    /**
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
