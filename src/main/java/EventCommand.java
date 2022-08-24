import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    String str;

    public EventCommand(String str) {
        this.str = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String sub = str.substring(6).trim();
        try {
            if (str.contains("/at ")) {
                String[] split = sub.split("/at ");
                if (split.length < 2) {
                    throw new DukeException("Please specify the event date in yyyy-mm-dd format.");
                } else {
                    LocalDate ld = LocalDate.parse(split[1]);
                    tasks.addTask(new Event(split[0], ld));
                    storage.saveLocalData(tasks.TASKS);
                    System.out.println("Got it, I've added this task:");
                    System.out.println(tasks.TASKS.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else {
                throw new DukeException("Please specify the event date by using \"/at \"");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the event date in yyyy-mm-dd format.");
        }
    }
}
