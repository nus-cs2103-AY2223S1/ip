package command;

import java.util.Optional;

import henry.TaskList;

/**
 * CommandResult is returned by all Commands.
 * It contains two methods; getTaskList, which returns
 * the TaskList modified by the Command (if applicable),
 * and toString, which returns the feedback specified
 * when the CommandResult is constructed.
 */
public class CommandResult {

    private final String feedback;
    private final TaskList list;

    public CommandResult(String feedback) {
        this(feedback, null);
    }

    /**
     * Constructs a CommandResult with the given feedback and TaskList.
     *
     * @param feedback the feedback to be displayed to the user. Usually
     *                 a success or error message.
     * @param list     the TaskList modified by the Command (if applicable).
     */
    public CommandResult(String feedback, TaskList list) {
        this.feedback = feedback;
        this.list = list;
    }

    /**
     * Gets the task list from the CommandResult, if it exists.
     *
     * @return the task list if it is present, otherwise returns an empty optional.
     */
    public Optional<TaskList> getTaskList() {
        return Optional.ofNullable(list);
    }

    @Override
    public String toString() {
        return feedback;
    }
}
