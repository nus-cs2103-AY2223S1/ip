package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.time.LocalDateTime;

public class EventCommand implements Command {
    private Event event;

    public EventCommand(String desc, LocalDateTime dateTime) {
        this.event = new Event(desc, false, dateTime);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(this.event);
            storage.appendToFile(this.event);
            ui.addTask(this.event);
            ui.printListSize(taskList);
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
