package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import java.time.LocalDateTime;

/**
 * Created when user inputs "deadline".
 */
public class DeadlineCommand extends Command {
    private String taskDetails;
    private LocalDateTime time;

    /**
     * Constructor for a deadline command.
     * @param taskDetails The description of the task.
     * @param time The time of the task, given in "yyyy-MM-dd HH:mm" format.
     */
    public DeadlineCommand(String taskDetails, LocalDateTime time) {
        this.taskDetails = taskDetails;
        this.time = time;
    }

    /**
     * Generates and saves a Deadline task in the task list.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     */
    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) {
        Task task = new Deadline(taskDetails, time);
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        storage.save(taskList.list());
    }
}
