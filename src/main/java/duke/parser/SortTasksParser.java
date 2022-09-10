package duke.parser;

import duke.commands.tasks.SortTasksCommand;
import duke.enums.SortTaskEnum;

/**
 * SortTasksParser Class
 */
public class SortTasksParser implements IParser<SortTasksCommand> {

    @Override
    public SortTasksCommand parse(String arguments) {
        if (SortTaskEnum.contains(arguments)) {
            return new SortTasksCommand(SortTaskEnum.valueOf(arguments));
        }
        return new SortTasksCommand(SortTaskEnum.ASC);
    }

}
