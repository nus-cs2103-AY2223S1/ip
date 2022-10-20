package duke.commands;

import duke.DukeException;
import duke.Task;
import duke.TaskList;

public class MarkCommand implements Command {
    public final static String COMMAND_WORD_COMPLETED = "mark";
    public final static String COMMAND_WORD_NOT_COMPLETED = "unmark";

    private int displayedTaskIndex;
    private boolean completed;

    public MarkCommand(int displayedTaskIndex, boolean completed) {
        this.displayedTaskIndex = displayedTaskIndex;
        this.completed = completed;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        Task task = taskList.markTask(displayedTaskIndex, completed);
        String successMessage = completed
                ? "Nice! I've marked this task as done:\n\t%s"
                : "OK, I've marked this task as not done yet:\n\t%s";
        return String.format(successMessage, task.toString());
    }
}
