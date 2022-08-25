package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

public class AddEventCommand extends Command {
    private String description;
    private String time;

    public AddEventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event;
        event = new Event(description, time);
        tasks.add(event);
        ui.printMessage("\tGot it. I've added this task:\n\t" +
                event.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAllTasks());
    }
}
