package duke.command;

import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate deadline;

    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        Deadline deadline = new Deadline(this.description, this.deadline);
        taskList.add(deadline,storage);
        String message = "Nice! This task has been successfully added!";
        ui.displayCommandMessage(message, deadline, taskList.getSize());
        ui.printBorder();
    }
}
