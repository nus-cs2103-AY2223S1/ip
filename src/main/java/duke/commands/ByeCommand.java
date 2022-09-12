package duke.commands;

import duke.task.TaskList;

public class ByeCommand extends Command {
    public String execute(TaskList taskList) {
        setIsExitToTrue();
        return "Bye. Hope to see you again soon!";
    }
}
