package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.tasks.DeadlineTask;
import jenny.util.UserInterface;
import jenny.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = DeadlineCommand.class.getSimpleName();
    public static final String COMMAND = "deadline";
    private static final Pattern VALID_DEADLINE_TASK = Pattern.compile(
            "(?<description>.+?)\\s/by\\s(?<dueDate>.+)");
    private static final String ERROR_INVALID_FORMAT =
            "You have entered an invalid format for deadline <description> /by <dueDate>.";
    private static final String ADD_DEADLINE_SUCCESS = "Got it. I've added this task:";

    public DeadlineCommand(String arguments) {
        super(COMMAND);
        super.arguments = arguments;
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        Matcher matcher = VALID_DEADLINE_TASK.matcher(arguments);
        if (!matcher.matches()) throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_FORMAT);
        String description = matcher.group("description").strip();
        String dueDate = matcher.group("dueDate").strip();

        try {
            LocalDate date = Validator.parseDate(dueDate);
            DeadlineTask task = new DeadlineTask(description, date);
            tasks.add(task);
            UserInterface.print(new String[]{
                    ADD_DEADLINE_SUCCESS,
                    "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list."
            });
        } catch (JennyException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}
