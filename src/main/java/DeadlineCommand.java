import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(String description, LocalDate time) {
        super();
        this.deadline = new Deadline(description, time);
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.deadline);
        ui.showAdd(this.deadline, list.getSize());
        storage.saveList(list.save());
    }
}
