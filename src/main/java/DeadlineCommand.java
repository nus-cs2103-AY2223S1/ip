import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime byDateTime;
    
    public DeadlineCommand(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    public static String getFormat() {
        return "deadline <String> /by <String>";
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deadline = new Deadline(this.description, this.byDateTime);
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + deadline);
        System.out.println("Now, you have " + tasks.size() + " tasks in the list");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
