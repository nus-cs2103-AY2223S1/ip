import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    String desc;
    LocalDateTime datetime;

    public DeadlineCommand(String desc, LocalDateTime datetime) {
        this.desc = desc;
        this.datetime = datetime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline tmp = new Deadline(desc, datetime);
        tasks.addDeadline(tmp);
        storage.write(tasks.toStringWritable());
        ui.showOutput("Got it. I added this task:");
        ui.showOutput("\t" + tmp);
        ui.showOutput("Now you have " + tasks.getLength() + " tasks in the list.");

    }

    public boolean isExit() {
        return false;
    }
}
