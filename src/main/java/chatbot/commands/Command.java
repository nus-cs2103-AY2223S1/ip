package chatbot.commands;

import chatbot.ui.Response;
import chatbot.ui.UI;
import chatbot.tasks.TaskList;

/**
 * Represents a command that can be executed by the chatbot.
 */
public interface Command {
    /**
     * The method should perform the required actions of represented by the command.
     *
     * @param todos The todo list the command will operate on when executed.
     * @param ui The ui responsible for showing the corresponding message to user
     *           when the command is executed.
     */
    void execute(TaskList todos, UI ui);

    String execute(TaskList todos, Response resp);

    /**
     * The method informs the caller if the command is an exit command.
     */
    boolean isExit();
}
