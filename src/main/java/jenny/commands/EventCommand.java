package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.tasks.DeadlineTask;
import jenny.tasks.EventTask;
import jenny.util.UserInterface;
import jenny.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = EventCommand.class.getSimpleName();
    public static final String COMMAND = "event";
    private static final Pattern VALID_EVENT_TASK = Pattern.compile(
            "(?<description>.+?)\\s/at\\s(?<dueDate>.+)");
    private static final String ERROR_INVALID_FORMAT =
            "You have entered an invalid format for deadline <description> /at <dueDate>.";
    private static final String ADD_EVENT_SUCCESS = "Got it. I've added this task:";

    public EventCommand(String arguments) {
        super(COMMAND);
        super.arguments = arguments;
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        Matcher matcher = VALID_EVENT_TASK.matcher(arguments);
        if (!matcher.matches()) throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_FORMAT);
        String description = matcher.group("description").strip();
        String dueDate = matcher.group("dueDate").strip();

        try {
            LocalDate date = Validator.parseDate(dueDate);
            EventTask task = new EventTask(description, date);
            tasks.add(task);
            UserInterface.print(new String[]{
                    ADD_EVENT_SUCCESS,
                    "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list."
            });
        } catch (JennyException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}
