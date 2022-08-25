package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class AddEventCommand extends Command {

    private String details;

    public AddEventCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!details.contains("/at")) {
            throw new DukeException("OOPS!!! Time of event required. (/at)");
        }
        String[] split = details.split("/");
        String desc = split[0];
        if (desc.equals("") || desc.equals(" ")) {
            throw new DukeException("OOPS!!! Description of event is required.");
        }
        String at = split[1].split(" ", 2)[1];
        Event event = new Event(desc, at);
        tasks.add(event);
    }
}
