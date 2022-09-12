package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import task.Event;

public class EventCommand extends Command {

    private final String STRING;

    public EventCommand(String str) {
        this.STRING = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String sub = STRING.substring(5).trim();
        try {
            if (STRING.contains("/at ")) {
                String[] split = sub.split("/at ");
                if (split.length < 2) {
                    throw new DukeException("Please specify the event date in yyyy-mm-dd format.");
                } else {
                    LocalDate ld = LocalDate.parse(split[1]);
                    tasks.addTask(new Event(split[0], ld));
                    storage.saveLocalData(tasks.getTasks());
                    StringBuilder output = new StringBuilder();
                    output.append("Got it, I've added this task:\n");
                    output.append(tasks.getTasks().get(tasks.size() - 1).toString());
                    output.append(String.format("\nNow you have %d tasks in the list.\n", tasks.size()));
                    return output.toString();
                }
            } else {
                throw new DukeException("Please specify the event date by using \"/at \"");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the event date in yyyy-mm-dd format.");
        }
    }
}
