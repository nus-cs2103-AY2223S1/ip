package duke.commands;

import duke.DukeException;
import duke.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddEventCommand extends Command {

    public AddEventCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] str = description.substring(6).split(" /at ");
            Event event = new Event(str[0], str[1], false);
            tasks.add(event);
            ui.printAddTask(event, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description and/or the time of an event cannot be empty.");
        }
    }
}
