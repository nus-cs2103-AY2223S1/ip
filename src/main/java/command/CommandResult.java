package command;

import henry.TaskList;

import java.util.Optional;

public class CommandResult {

    public final String feedback;
    private final TaskList list;

    public CommandResult(String feedback) {
        this.feedback = feedback;
        this.list = null;
    }

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
