package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.time.LocalDateTime;

/**
 * This class tells Duke to store a new task with a deadline.
 */
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
            ui.sayAddTask(this.deadline);
            ui.sayTaskListSize(taskList);
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
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
