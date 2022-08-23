package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private Event ev;

    public static final String COMMAND_WORD = "event";

    public EventCommand(Event ev) {
        this.ev = ev;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(ev);
    }
}
