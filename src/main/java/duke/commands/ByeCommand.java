package duke.commands;

import duke.task.TaskList;

import static duke.ui.Messages.MESSAGE_FAREWELL;

public class ByeCommand extends Command {
    public String execute(TaskList taskList) {
        setIsExitToTrue();
        return MESSAGE_FAREWELL;
    }
}
