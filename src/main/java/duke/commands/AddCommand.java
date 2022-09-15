package duke.commands;

import duke.Storage;
import duke.DukeException;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class AddCommand extends Command {
    public Task task;

    public AddCommand(String instruction, Task task) {
        super(instruction);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        ui.print(ui.addLineBreak(
                "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list."));
        storage.saveList(tasks);
    }
}
