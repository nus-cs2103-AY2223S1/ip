package duke.util.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.SaveTasks;

public abstract class Command {
    public String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract String handleCommand(TaskList taskList, SaveTasks saveTasks) throws DukeException;
}
