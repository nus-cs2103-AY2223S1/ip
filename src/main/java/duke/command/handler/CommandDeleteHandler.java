package duke.command.handler;

import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.command.response.DeleteTaskResponse;

import duke.data.TaskList;
import duke.data.tasks.Task;

import java.util.regex.Pattern;
import java.util.regex.MatchResult;

public class CommandDeleteHandler extends CommandHandler {

    protected static final String INVALID_FORMAT_MSG = String.join("\n",
        "Invalid `delete` command format!",
        "Expected format: delete <task-number>",
        "Examples:",
        "\t- delete 1"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("^delete (\\d+)");

    public CommandDeleteHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MSG;
    }

    /**
     * Delete a task from the task list
     *
     * @param taskList task list
     * @return delete task response
     * @throws CommandException if task number given is out of range or task number string cannot be
     *                          parsed into a number
     */
    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        String taskIdxStr = regexMatchResult.group(1);
        try {
            int taskIdx = Integer.parseInt(taskIdxStr);
            if (taskIdx <= 0 || taskIdx > taskList.size()) {
                throw new CommandException("Invalid task selected!");
            } else {
                Task deletedTask = taskList.deleteTask(taskIdx - 1);
                return new DeleteTaskResponse(deletedTask, taskList.size());
            }
        } catch (NumberFormatException error) {
            throw new CommandException(String.join("\n",
                "Task number should be a number!",
                "Got: %s", taskIdxStr
            ));
        }
    }
}
