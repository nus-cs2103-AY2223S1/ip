package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command {
    private String description;
    private LocalDate at;

    public EventCommand(String description, LocalDate at) {
        super();
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addEvent(description, at);
        ui.showAdd(task, tasks.getSize());
        storage.save(tasks.saveTasks());
    }
}
