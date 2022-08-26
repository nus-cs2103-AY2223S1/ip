package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.time.LocalDate;

public class DeadlineCommand implements Command {

    private Deadline deadline;

    public DeadlineCommand(String desc, LocalDate date) {
        this.deadline = new Deadline(desc, false, date);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(deadline);
            storage.appendToFile(deadline);
            ui.addTask(deadline);
            ui.printListSize(taskList);
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
