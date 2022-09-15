package duke.commands;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.DeadlinesTask;

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
     * @return String output of executing the task.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        DeadlinesTask newTask = new DeadlinesTask(taskName, deadlineDate);
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
