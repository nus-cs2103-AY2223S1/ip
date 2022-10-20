package duke.commands;

import duke.Task;
import duke.TaskList;

public class AddCommand implements Command {
    public static final String COMMAND_WORD_TODO = "todo";
    public static final String COMMAND_WORD_TODO_SHORT = "t";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String COMMAND_WORD_EVENT_SHORT = "e";
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_DEADLINE_SHORT = "d";

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList) {
        taskList.addTask(task);
        return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task.toString(),
                taskList.getNumberOfTasks());
    }
}
