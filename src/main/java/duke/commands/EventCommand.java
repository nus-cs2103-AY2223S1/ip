package duke.commands;

import duke.tasks.EventTask;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;

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
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        EventTask newTask = new EventTask(eventName,eventLocationString);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
        storage.writeAll(tasks);
    }
}
