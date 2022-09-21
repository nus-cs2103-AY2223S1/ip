package catbot.command;

import catbot.TaskList;
import catbot.Ui;
import catbot.exception.EmptyDurationException;
import catbot.exception.EmptyTaskException;
import catbot.exception.InvalidArgumentException;
import catbot.task.Event;

/**
 * A class for the event command.
 */
public class EventCommand extends Command {

    /** The description of the event task as input by the user. */
    private final String description;

    /**
     * Constructor for the EventCommand class.
     *
     * @param description The description of the event task as input by the user.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the event command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws EmptyTaskException       If the followup text after the command is empty.
     * @throws InvalidArgumentException If the followup text after the command and description is not "/at".
     * @throws EmptyDurationException   If the followup text after "/at" is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyTaskException, InvalidArgumentException,
            EmptyDurationException {
        String[] split = this.description.split("/at ");
        if (isEmptyTask(split)) {
            throw new EmptyTaskException("event");
        }
        if (split[0].equals(this.description)) {
            throw new InvalidArgumentException("event", "/at");
        }
        if (split.length == 1 || split[1].trim().equals("")) {
            throw new EmptyDurationException("event", "/at");
        }
        taskList.add(new Event(split[0].trim(), split[1]));
        this.response += "Added the event task: \n  " + taskList.getLast() + "\n" + ui.printListCount();
    }

    /**
     * Checks if the input text is an empty command to add a task.
     *
     * @param split The string array of words split up.
     * @return True if the task is empty, false otherwise.
     */
    private boolean isEmptyTask(String[] split) {
        return this.description.trim().equals("") || split.length == 0 || split[0].equals("");
    }
}
