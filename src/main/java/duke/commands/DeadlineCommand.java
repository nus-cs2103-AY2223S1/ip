package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand implements Command {

    private Deadline deadline;

    public DeadlineCommand(String desc, LocalDateTime dateTime) {
        this.deadline = new Deadline(desc, dateTime);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(this.deadline);
            storage.appendToFile(this.deadline);
            ui.addTask(this.deadline);
            ui.showTaskListCapacity(taskList);
        } catch (DukeException e) {
            ui.handleException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeadlineCommand) {
            DeadlineCommand that = (DeadlineCommand) o;
            if (this.deadline.equals(that.deadline)) {
                return true;
            }
        }
        return false;
    }
}
