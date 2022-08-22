package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.UI;

public class AddTaskCommand extends Command {
    public AddTaskCommand(CommandType command, String[] args) {
        super(command, args);
    }

    @Override
    public void execute(TaskList tasks) {
        Task task = Task.of(this.command, this.args);
        tasks.add(task);
        tasks.save();
        UI.print("I've added the following task:\n\t" + task);
    }
}
