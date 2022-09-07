package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.EventTask;

/**
 * Command that represents the adding of a event task.
 */
public class EventCommand implements Command {
    private String eventName;
    private String eventLocationString;

    /**
     * Default constructor of the event command.
     *
     * @param eventName Name of the event task.
     * @param eventLocationString Location of the event task.
     */
    public EventCommand(String eventName, String eventLocationString) {
        this.eventName = eventName;
        this.eventLocationString = eventLocationString;
    }

    /**
     * Runs the event command by adding the event task into the tasklist and storage.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @return String output of executing the task.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        EventTask newTask = new EventTask(eventName, eventLocationString);
        tasks.add(newTask);
        storage.writeAll(tasks);
        String output = "";
        output += "Got it. I've added this task:\n";
        output += newTask + "\n";
        output += "Now you have " + tasks.getSize() + " tasks in the list\n";
        return output;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
