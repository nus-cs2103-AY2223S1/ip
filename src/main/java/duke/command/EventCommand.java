package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidTimeException;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand extends Command {

    String desc;
    String time;

    public EventCommand(String desc, String time) throws InvalidDescriptionException, InvalidTimeException {
        if (desc.isEmpty()) {
            throw new InvalidDescriptionException();
        }
        if (time.isEmpty()) {
            throw new InvalidTimeException();
        }

        this.desc = desc;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.desc, this.time);
        taskList.addTask(event);
        storage.saveTaskFile(taskList);

    }
}
