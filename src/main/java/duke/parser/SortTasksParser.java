package duke.parser;

import duke.commands.tasks.SortTasksCommand;
import duke.enums.SortTaskEnum;

/**
 * SortTasksParser Class
 */
public class SortTasksParser implements IParser<SortTasksCommand> {

    @Override
    public SortTasksCommand parse(String arguments) {
        if (arguments.contains(SortTasksCommand.SUBCOMMAND_WORD)) {
            return new SortTasksCommand(SortTaskEnum.DESC);
        }
        return new SortTasksCommand(SortTaskEnum.ASC);
    }

}
