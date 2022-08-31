package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

public class Parser {
    /**
     * Returns parsed command from user input based on keywords.
     * If there are no matching commands, a DukeException is thrown.
     *
     * @param userInput Input string from the user.
     * @return A Command object based on parsed input.
     */
    public static Command parse(String userInput) throws DukeException {
        String command;

        if (userInput.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The user input cannot be empty.");
        }
        command = userInput.split(" ")[0];
        if (command.equals(Duke.Keyword.EXIT.getKeyword())) {
            return new ExitCommand();
        } else if (command.equals(Duke.Keyword.LIST.getKeyword())) {
            return new ListCommand();
        } else if (command.equals(Duke.Keyword.MARK.getKeyword()) || command.equals(Duke.Keyword.UNMARK.getKeyword())) {
            return new MarkCommand(userInput, command.equals(Duke.Keyword.MARK.getKeyword()) ? true : false);
        } else if (command.equals(Duke.Keyword.TODO.getKeyword())) {
            return new AddCommand(userInput);
        } else if (command.equals(Duke.Keyword.EVENT.getKeyword())) {
            return new AddCommand(userInput);
        } else if (command.equals(Duke.Keyword.DEADLINE.getKeyword())) {
            return new AddCommand(userInput);
        } else if (command.equals(Duke.Keyword.DELETE.getKeyword())) {
            return new DeleteCommand(userInput);
        }else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
