import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private static final Pattern ARGUMENTS_FORMAT = Pattern.compile("(?<description>.+)\\s+/by\\s+(?<deadline>\\d{4}-\\d{2}-\\d{2})");
    private static final String userMessageFormat = "Added this deadline!\n  %s\nNow you have %d tasks.";
    private final Deadline deadline;

    public DeadlineCommand(String arguments) throws DukeException {
        Matcher matcher = ARGUMENTS_FORMAT.matcher(arguments);
        String description, deadline;
        if (matcher.matches()) {
            description = matcher.group("description");
            deadline = matcher.group("deadline");
        } else {
            throw Deadline.wrongFormat;
        }

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw DukeException.invalidDate;
        }

        this.deadline = new Deadline(description, localDate);
    }

    @Override
    public CommandResult execute() {
        this.tasks.addTask(this.deadline);
        int numberOfTasks = this.tasks.size();
        String userMessage = String.format(userMessageFormat, this.deadline, numberOfTasks);
        return new CommandResult(userMessage, true, false);
    }
}
