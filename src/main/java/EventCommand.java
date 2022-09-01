import java.time.LocalDateTime;

public class EventCommand extends Command {
    String desc;
    LocalDateTime datetime;

    public EventCommand(String desc, LocalDateTime datetime) {
        this.desc = desc;
        this.datetime = datetime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event tmp = new Event(desc, datetime);
        tasks.addEvent(tmp);
        storage.write(tasks.toStringWritable());
        ui.showOutput("Got it. I added this task:");
        ui.showOutput("\t" + tmp);
        ui.showOutput("Now you have " + tasks.getLength() + " tasks in the list.");

    }

    public boolean isExit() {
        return false;
    }
}
