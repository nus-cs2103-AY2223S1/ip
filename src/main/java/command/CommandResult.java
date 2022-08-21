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

    public Optional<TaskList> getTaskList() {
        return Optional.ofNullable(list);
    }
}
