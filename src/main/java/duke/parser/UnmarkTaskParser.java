package duke.parser;

import duke.commands.tasks.UnmarkTaskCommand;
import duke.domain.task.TaskIndex;
import duke.exceptions.ParseException;

public class UnmarkTaskParser implements IParser<UnmarkTaskCommand> {

    public UnmarkTaskCommand parse(String arguments) throws ParseException {
        if (ParserUtil.isNumeric(arguments)) {
            TaskIndex taskIndex = TaskIndex.fromOneBased(Integer.parseInt(arguments));
            return new UnmarkTaskCommand(taskIndex);
        } else {
            throw new ParseException(
                    "Sorry the second argument is not a number");
        }
    }

}
