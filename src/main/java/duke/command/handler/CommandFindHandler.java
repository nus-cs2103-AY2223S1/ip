package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.data.TaskList;

public class CommandFindHandler extends CommandHandler {

    protected static final String INVALID_FORMAT_MSG = String.join("\n",
        "Invalid `find` command format!",
        "Expected format: find <keyword>"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("^find (.+)");

    public CommandFindHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MSG;
    }

    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        String keyword = regexMatchResult.group(1);

        return new CommandResponse(taskList.findTasks(keyword).toString(), false, false);
    }
}
