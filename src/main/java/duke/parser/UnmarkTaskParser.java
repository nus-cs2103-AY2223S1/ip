package duke.parser;

import duke.commands.tasks.UnmarkTaskCommand;
import duke.domain.task.TaskIndex;
import duke.exceptions.ParseException;

/**
 * UnmarkTaskParser class
 */
public class UnmarkTaskParser implements IParser<UnmarkTaskCommand> {

    @Override
    public UnmarkTaskCommand parse(String arguments) throws ParseException {
        if (!ParserUtil.isNumeric(arguments)) {
            throw new ParseException(
                    "Sorry the second argument is not a number");
        }
        TaskIndex taskIndex = TaskIndex.fromOneBased(Integer.parseInt(arguments));
        return new UnmarkTaskCommand(taskIndex);
    }

}
