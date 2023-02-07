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
        String taskIndexString = arguments.trim();
        if (ParserUtil.isNumeric(taskIndexString)) {
            throw new ParseException(
                    "Sorry the second argument is not a number");
        }
        TaskIndex taskIndex = TaskIndex.fromOneBased(Integer.parseInt(taskIndexString));
        return new DeleteTaskCommand(taskIndex);
    }

}
