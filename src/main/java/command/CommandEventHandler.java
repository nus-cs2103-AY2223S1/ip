package command;

import data.TaskList;
import data.tasks.TaskEvent;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import util.CommandUtils;

public class CommandEventHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^event (.+) /at (.+)");

    CommandEventHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(
                "Invalid `event` command format (expected: event event-title /at datetime)");
        }
    }

    @Override
    public List<String> run(TaskList taskList) {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        String eventTitle = regexMatchResult.group(1);
        String eventDateTimeStr = regexMatchResult.group(2);

        TaskEvent eventTask = new TaskEvent(eventTitle, eventDateTimeStr);
        taskList.addTask(eventTask);

        return CommandUtils.generateAddTaskResponse(eventTask, taskList.size());
    }
}
