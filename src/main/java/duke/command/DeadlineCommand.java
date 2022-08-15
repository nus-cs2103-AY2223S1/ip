package duke.command;

import duke.TaskList;
import duke.Ui;

public class DeadlineCommand extends Command {
    String description;
    String by;

    public DeadlineCommand(String description, String by) {
        super();
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        String task = tasks.addDeadline(this.description, this.by);
        ui.showAdd(task, tasks.getSize());
    }
}