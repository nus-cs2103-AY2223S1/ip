package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.task.ToDo;
import jarvis.ui.Ui;

/**
 * AddCommand --- command to create todos, deadlines and events.
 */
public class AddCommand extends Command {
    /**
     * Constructor.
     *
     * @param command the command entered by the user.
     */
    public AddCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     *
     * @param storage stores the tasks locally.
     * @param tasks the list of tasks.
     * @param ui prints feedback.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws JarvisException {
        Task task;
        switch(super.getKeyCommand()) {
        case "todo":
            task = new ToDo(super.getDescription());
            tasks.add(task);
            storage.saveTasks(tasks);
            return ui.showTaskAddedMessage(task, tasks.size());
        case "deadline":
            task = new Deadline(super.getDescription(), super.getDateTime());
            tasks.add(task);
            storage.saveTasks(tasks);
            return ui.showTaskAddedMessage(task, tasks.size());
        case "event":
            task = new Event(super.getDescription(), super.getDateTime());
            tasks.add(task);
            storage.saveTasks(tasks);
            return ui.showTaskAddedMessage(task, tasks.size());
        default:
            throw new JarvisException("Unrecognised. Please enter a valid command.");
        }
    }
}
