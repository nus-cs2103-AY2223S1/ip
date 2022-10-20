package duke.parser;

import duke.DukeException;
import duke.Todo;
import duke.commands.AddCommand;
import duke.commands.Command;

public class TodoCommandParser implements Parser {
    @Override
    public Command parse(String arguments) throws DukeException {
        String trimmed = arguments.trim();
        if (trimmed.length() == 0) {
            throw new DukeException("Please add a description for the todo");
        }

        Todo todo = new Todo(trimmed);

        return new AddCommand(todo);
    }
}
