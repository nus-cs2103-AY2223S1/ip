package duke.commands;

import duke.tasks.DeadlinesTask;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;
import java.time.LocalDateTime;

public class DeadlineCommand implements Command {
    private String taskName;
    private LocalDateTime deadlineDate;

    public DeadlineCommand(String taskName, LocalDateTime deadlineDate) {
        this.taskName = taskName;
        this.deadlineDate = deadlineDate;
    }

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
