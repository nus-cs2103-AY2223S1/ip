package duke.commands;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private Event ev;

    public EventCommand(Event ev) {
        this.ev = ev;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(ev);
    }
}
