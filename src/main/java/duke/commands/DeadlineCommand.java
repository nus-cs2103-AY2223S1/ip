package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Deadline(description, by);
            tasks.addTask(newTask);
            ui.sendMessage(newTask.getAddMessage(tasks.getSize()));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}