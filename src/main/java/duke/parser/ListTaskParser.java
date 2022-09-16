package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.tasks.ListTasksAfterCommand;
import duke.commands.tasks.ListTasksBeforeCommand;
import duke.commands.tasks.ListTasksCommand;

/**
 * ListTaskParser class
 */
public class ListTaskParser implements IParser<ListTasksCommand> {
    private static final Pattern LIST_TASKS_COMMAND_FORMAT = Pattern.compile(
            "(?<subCommandWord>^\\s*\\w+)(?<subArgs>.*)");

    @Override
    public ListTasksCommand parse(String args) {
        Matcher matcher = LIST_TASKS_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new ListTasksCommand();
        }
        final String subCommandWord = matcher
                .group("subCommandWord")
                .trim();
        final String subArgs = matcher
                .group("subArgs")
                .trim();
        switch (subCommandWord) {
        case ListTasksBeforeCommand.SUBCOMMAND_WORD:
            return new ListTasksBeforeCommand(
                    ParserUtil.parseDateTimeString(subArgs));
        case ListTasksAfterCommand.SUBCOMMAND_WORD:
            return new ListTasksAfterCommand(
                    ParserUtil.parseDateTimeString(subArgs));
        default:
            return new ListTasksCommand();
        }
    }

}
