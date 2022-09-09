package duke.parser;

import duke.commands.tasks.ListTasksAfterCommand;
import duke.commands.tasks.ListTasksBeforeCommand;
import duke.commands.tasks.ListTasksCommand;

public class ListTaskParser implements IParser<ListTasksCommand> {

    @Override
    public ListTasksCommand parse(String arguments) {
        String advancedListText = String.join(" ", arguments);
        if (advancedListText.contains(ListTasksBeforeCommand.SUBCOMMAND_WORD)) {
            String[] advancedListArgs = advancedListText.split(
                    ListTasksBeforeCommand.SUBCOMMAND_WORD);
            String advancedListDateTime = advancedListArgs[1];
            return new ListTasksBeforeCommand(
                    ParserUtil.parseDateTimeString(advancedListDateTime));
        } else if (advancedListText.contains(ListTasksAfterCommand.SUBCOMMAND_WORD)) {
            String[] advancedListArgs = advancedListText.split(
                    ListTasksAfterCommand.SUBCOMMAND_WORD);
            String advancedListDateTime = advancedListArgs[1];
            return new ListTasksAfterCommand(
                    ParserUtil.parseDateTimeString(advancedListDateTime));
        } else {
            return new ListTasksCommand();
        }
    }

}
