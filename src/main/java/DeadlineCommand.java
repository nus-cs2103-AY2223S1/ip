import java.time.LocalDateTime;

public class DeadlineCommand extends Command{
    private String taskDetails;
    private LocalDateTime time;

    DeadlineCommand(String taskDetails, LocalDateTime time) {
        this.taskDetails = taskDetails;
        this.time = time;
    }

    @Override
    void execute(Storage storage, UI ui, TaskList taskList) {
        Task task = new Deadline(taskDetails, time);
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        storage.save(taskList.list());
    }
}
