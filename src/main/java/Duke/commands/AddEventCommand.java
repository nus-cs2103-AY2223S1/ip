package Duke.commands;

import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.tasks.Event;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class AddEventCommand extends Command {

    private final String input;

    public AddEventCommand(String input) throws DukeException {
        if (!checkValid(input))
            throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
        if (!input.contains("/at")) throw new DukeException(" ☹ OOPS!!! Please enter in the format : \n" +
                "event {task description} /at {date/time}");
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        String taskDesc = input.substring(6, input.indexOf('/') - 1);
        String deadline = input.substring(input.indexOf('/') + 3);
        Event event = new Event(taskDesc, deadline);
        taskList.addTask(event);
        ui.addTaskMessage(event, taskList.size());
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
