package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {

    private final String description;
    private final LocalDate at;

    public EventCommand(String description, LocalDate at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Event(description, at);
            tasks.addTask(newTask);
            ui.sendMessage(newTask.getAddMessage(tasks.getSize()));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        tasks.updateStorage();
    }
}
