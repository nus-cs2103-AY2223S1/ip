package duke.command;

import duke.Storage;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidTimeException;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Represents an Event Command
 */
public class EventCommand extends Command {

    String desc;
    String time;

    /**
     * Creates an Event Command object
     */
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

    /**
     * Creates an Event object
     * adds it to tasklist object
     * saves tasklist to task file
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Event event = new Event(this.desc, this.time);
        taskList.addTask(event);
        storage.saveTaskFile(taskList);

    }
}
