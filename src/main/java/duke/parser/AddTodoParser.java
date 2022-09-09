package duke.parser;

import duke.commands.tasks.AddTodoCommand;
import duke.domain.Todo;
import duke.exceptions.ParseException;

/**
 * AddTodoParser Class
 */
public class AddTodoParser implements IParser<AddTodoCommand> {

    @Override
    public AddTodoCommand parse(String arguments) throws ParseException {
        if (arguments.length() > 0) {
            Todo newTodo = Todo.of(arguments);
            return new AddTodoCommand(newTodo);
        } else {
            throw new ParseException(
                    "The description of the todo cannot be empty!");
        }
    }

}
