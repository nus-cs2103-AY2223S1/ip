package command;

import data.TaskList;
import data.tasks.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CommandMarkHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^(u?n?mark) (\\d+)");

    CommandMarkHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(
                "`mark`/`unmark` command expects a single number argument (e.g. `mark 1`, `unmark 2`)");
        }
    }

    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        List<String> responseList = new ArrayList<>();
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        boolean toMark = regexMatchResult.group(1).equals("mark");
        String taskIdxStr = regexMatchResult.group(2);
        try {
            int taskIdx = Integer.parseInt(taskIdxStr);
            if (taskIdx <= 0 || taskIdx > taskList.size()) {
                throw new CommandException("Invalid task selected!");
            } else {
                Task task = taskList.getTask(taskIdx - 1);
                if (toMark) {
                    task.mark();
                    responseList.add("Nice! I've mark this task as done:");
                } else {
                    task.unmark();
                    responseList.add("OK, I've marked this task as not done yet:");
                }
                responseList.add(String.format("\t%s", task));
            }
        } catch (NumberFormatException error) {
            throw new CommandException(
                String.format("`mark`/`unmark` expects a number argument. Got: %s", taskIdxStr));
        }

        return new CommandResponse(responseList, true);
    }
}
