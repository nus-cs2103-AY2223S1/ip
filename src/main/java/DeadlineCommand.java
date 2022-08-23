import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(String description, LocalDate time) {
        super();
        this.deadline = new Deadline(description, time);
    }

    @Override
    public void execCommand(TaskList list, Save save) {
        list.addTask(this.deadline);
        System.out.println("Successfully added new task:\n" + this.deadline +
                "\nYou have " + list.getSize() + " task(s) in the list.");
        save.saveList(list.save());
    }
}
