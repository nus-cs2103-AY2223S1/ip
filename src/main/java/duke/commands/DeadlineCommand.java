package duke.commands;

import duke.tasks.DeadlinesTask;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;
import java.time.LocalDateTime;

/**
 * Command that represents the adding of a deadline task.
 */
public class DeadlineCommand implements Command {
    private String taskName;
    private LocalDateTime deadlineDate;

    /**
     * Default constructor of the deadline command.
     *
     * @param taskName Name of the deadline task.
     * @param deadlineDate Date of the deadline task.
     */
    public DeadlineCommand(String taskName, LocalDateTime deadlineDate) {
        this.taskName = taskName;
        this.deadlineDate = deadlineDate;
    }

    /**
     * Runs the command by adding the deadline task into the tasklist and storage.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        DeadlinesTask newTask = new DeadlinesTask(taskName, deadlineDate);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
        storage.writeAll(tasks);
    }
}
