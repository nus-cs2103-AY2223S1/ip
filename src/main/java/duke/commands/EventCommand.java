package duke.commands;

import duke.*;
import duke.task.Event;

public class EventCommand extends Command {
    private final String input;

    public EventCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("Hold up! Description cannot be empty!");
        }

        String[] str = input.split(" /at ", 2);
        if (str.length < 2 || str[1].trim().length() == 0) {
            throw new DukeException("Wait! When is this event??");
        }
        Event newEvent = new Event(str[0], str[1]);

        taskList.addTask(newEvent);
        ui.showAddTask(newEvent, taskList);
    }

    public boolean isExit() {
        return false;
    }
}
