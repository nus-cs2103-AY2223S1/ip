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
        Deadlines newTask = new Deadlines(taskName, deadlineDate);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
        storage.writeAll(tasks);
    }
}
