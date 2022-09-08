import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends CreateTaskCommand {

    private final static Pattern descriptionPattern = Pattern.compile("(?<taskName>.*) /by (?<by>.*)");

    public DeadlineCommand(String fullInput) {
        super(fullInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        Matcher match = descriptionPattern.matcher(description);
        if (!match.matches()) {
            throw new IncompatibleCommandException("A deadline task without a deadline?");
        }
        ui.printLine("I've added this task:");
        Task addedTask = tasks.addTask(new Deadline(match.group("taskName"), match.group("by")));
        ui.printLine(addedTask);
        storage.addTask(addedTask);
        super.execute(tasks, ui, storage);
    }
}
