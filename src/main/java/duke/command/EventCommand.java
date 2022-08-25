package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final String description;
    private final LocalDate period;

    public EventCommand(String description, LocalDate period) {
        this.description = description;
        this.period = period;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        Event event = new Event(this.description, this.period);
        taskList.add(event,storage);
        String message = "Nice! This task has been successfully added!";
        ui.displayCommandMessage(message, event, taskList.getSize());
        ui.printBorder();
    }
}
