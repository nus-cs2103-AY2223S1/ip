package duke.parser;

import duke.commands.tasks.MarkTaskCommand;
import duke.domain.task.TaskIndex;
import duke.exceptions.ParseException;

/**
 * MarkTaskParser class
 */
public class MarkTaskParser implements IParser<MarkTaskCommand> {

    @Override
    public MarkTaskCommand parse(String arguments) throws ParseException {
        if (!ParserUtil.isNumeric(arguments)) {
            throw new ParseException(
                    "Sorry the second argument is not a number");
        }
        TaskIndex taskIndex = TaskIndex.fromOneBased(Integer.parseInt(arguments));
        return new MarkTaskCommand(taskIndex);
    }

}
