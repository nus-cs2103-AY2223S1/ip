package duke.parser;

import duke.DukeException;
import duke.commands.Command;
import duke.commands.MarkCommand;

public class MarkCommandParser implements Parser {
    @Override
    public Command parse(String arguments) throws DukeException {
        try {
            int displayedIndex = Integer.parseInt(arguments.trim());
            return new MarkCommand(displayedIndex, true);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number");
        }
    }
}
