package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.EventTask;
import duke.task.Task;

/**
 * Can be executed to add an EventTask.
 */
public class AddEventCommand extends Command {

    private final Task eventTask;

    public AddEventCommand(String taskName, String date, String time) {
        eventTask = new EventTask(taskName, date, time);
    }

    public AddEventCommand(String taskName, String date) {
        eventTask = new EventTask(taskName, date);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(eventTask);
            storage.writeToFile(taskList.list());
            ui.display(String.format("Added new event task:%n%s%n", eventTask));
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            taskList.add(eventTask);
            storage.writeToFile(taskList.list());
            return String.format("I've added a new event task:%n%s%n", eventTask);
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

}
