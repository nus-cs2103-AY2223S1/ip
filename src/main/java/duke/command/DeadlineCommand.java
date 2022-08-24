package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.DukeException;
import duke.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private String description;
    private LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        ui.printMessage("+ Added this deadline:\n" + deadline + "\nNow you have " + taskList.listSize()
                + " tasks in the list\n");
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
