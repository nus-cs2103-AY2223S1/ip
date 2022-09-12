package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.handler.base.CommandUpdateTaskHandler;
import duke.command.response.CommandResponse;
import duke.command.response.UpdateTaskResponse;
import duke.command.response.UpdateTaskResponse.UpdateType;
import duke.data.TaskList;
import duke.data.tasks.Task;

public class CommandMarkHandler extends CommandUpdateTaskHandler {

    protected static final String INVALID_FORMAT_MESSAGE = String.join("\n",
        "Invalid `mark`/`unmark` command format!",
        "Expected format: mark <task-number> / unmark <task-number>",
        "Examples:",
        "\t- mark 1",
        "\t- unmark 1"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("^(u?n?mark) (\\d+)");

    public CommandMarkHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MESSAGE;
    }

    @Override
    protected String getSelectedTaskIdStr() {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        return regexMatchResult.group(2);
    }

    @Override
    protected CommandResponse updateTask(Task task, int taskIdx, TaskList taskList) {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        boolean toMark = regexMatchResult.group(1).equals("mark");

        if (toMark) {
            task.mark();
        } else {
            task.unmark();
        }
        return new UpdateTaskResponse(task, toMark ? UpdateType.MARK : UpdateType.UNMARK);
    }
}
