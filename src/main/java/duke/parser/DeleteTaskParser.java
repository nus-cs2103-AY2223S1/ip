package duke.parser;

import duke.commands.tasks.DeleteTaskCommand;
import duke.domain.task.TaskIndex;
import duke.exceptions.ParseException;

/**
 * DeleteTaskParser Class
 */
public class DeleteTaskParser implements IParser<DeleteTaskCommand> {

    @Override
    public DeleteTaskCommand parse(String arguments) throws ParseException {
        if (!ParserUtil.isNumeric(arguments)) {
            throw new ParseException(
                    "Sorry the second argument is not a number");
        }
        TaskIndex taskIndex = TaskIndex.fromOneBased(Integer.parseInt(arguments));
        return new DeleteTaskCommand(taskIndex);
    }

}
