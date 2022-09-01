package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Deadline;

public class DeadlineCommand extends Command {
    private String task;
    private String by;

    public DeadlineCommand(String task, String by) {
        super();
        this.task = task;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Deadline(task, by));
        return ui.printAddTaskSuccessfully(tasks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof DeadlineCommand == false) {
            return false;
        }
        DeadlineCommand that = (DeadlineCommand) o;
        return task.equals(that.task) &&
                by.equals(that.by);
    }
}
