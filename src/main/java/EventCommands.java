import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommands extends Executor {

    private final static Pattern descriptionPattern = Pattern.compile("(?<taskName>.*) /at (?<at>.*)");

    public EventCommands(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Matcher match = descriptionPattern.matcher(description);
        if (!match.matches()) {
            throw new DukeException("No event time was given, try again");
        }
        ui.printLine("I've added this task:");
        Task addedTask = tasks.addTask(new Event(match.group("taskName"), match.group("at")));
        ui.printLine(addedTask);
        storage.addTask(addedTask);
        super.execute(tasks, ui, storage);
    }
}
