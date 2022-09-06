package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {
    private String details;
    private String time;

    public EventCommand(String details, String time) {
        this.details = details;
        this.time = time;
    }

    @Override
    public void execute(TaskList list) throws DukeException {
        Event task = new Event(details, time);
        list.add(task);
        Ui.added(task);
    }
}