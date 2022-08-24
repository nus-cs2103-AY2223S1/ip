package duke.command.handler;

import duke.command.CommandException;
import duke.command.response.AddTaskResponse;
import duke.command.response.CommandResponse;

import duke.data.TaskList;
import duke.data.tasks.TaskTodo;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CommandTodoHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^todo (.+)");

    public CommandTodoHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(String.join("\n",
                "Invalid `todo` command format!",
                "Expected format: todo <task-title>",
                "Examples:",
                "\t- todo task-1"
            ));
        }
    }

    @Override
    public CommandResponse run(TaskList taskList) {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();

        TaskTodo todoTask = new TaskTodo(regexMatchResult.group(1));
        taskList.addTask(todoTask);

        return new AddTaskResponse(todoTask, taskList.size());
    }
}
