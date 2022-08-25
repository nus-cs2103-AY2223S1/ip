package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command{
    private String taskDetails;
    private LocalDateTime time;

    public EventCommand(String taskDetails, LocalDateTime time) {
        this.taskDetails = taskDetails;
        this.time = time;
    }

    /**
     * Generates and saves an Event task in the task list.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     */
    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) {
        Task task = new Event(taskDetails, time);
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        storage.save(taskList.list());
    }
}
