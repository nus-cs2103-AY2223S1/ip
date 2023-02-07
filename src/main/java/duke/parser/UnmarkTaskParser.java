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
        String taskIndexString = arguments.trim();
        if (ParserUtil.isNumeric(taskIndexString)) {
            throw new ParseException(
                    "Sorry the second argument is not a number");
        }
        TaskIndex taskIndex = TaskIndex.fromOneBased(Integer.parseInt(taskIndexString));
        return new UnmarkTaskCommand(taskIndex);
    }

}
