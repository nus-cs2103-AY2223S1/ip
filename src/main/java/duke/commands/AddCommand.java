package duke.commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    public static final String ADD_TODO = "todo";
    public static final String ADD_DEADLINE = "deadline";
    public static final String ADD_EVENT = "event";

    public static final String MESSAGE_SUCCESS = "Got it! I've added this task: \n"
            + "%1$s" + "\n"
            + "You have " + "%2$s" + " tasks in the list.\n";

    public static final String MESSAGE_TASK_DUPLICATION = "Eh, seems like you already have this task! \n"
            + "%1$s" + "\n"
            + "You have " + "%2$s" + " tasks in the list.\n";

    private final Task taskToAdd;
    private Task duplicatedTask;
    private final Set<String> commandDescription;

    /**
     * Adds a task to the list.
     */
    public AddCommand(Task taskToAdd, Set<String> commandDescription) {
        this.taskToAdd = taskToAdd;
        this.commandDescription = commandDescription;
    }
    public Task getTask() {
        return taskToAdd;
    }

    @Override
    public String execute(List tasks, Ui ui, Storage storage) {
        if (hasDuplicateTask(commandDescription, tasks)) {
            return ui.showToUser(String.format(MESSAGE_TASK_DUPLICATION, duplicatedTask, tasks.numberOfTasks()));
        }
        try {
            assert taskToAdd != null;
            tasks.addTask(taskToAdd);
            storage.save();
            return ui.showToUser(String.format(MESSAGE_SUCCESS, taskToAdd, tasks.numberOfTasks()));
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Returns true if there is any task that has the same description with the
     * new task user intends to add.
     *
     * @param commandDescription for searching
     * @return boolean
     */
    private boolean hasDuplicateTask(Set<String> commandDescription, List tasks) {
        for (Task task : tasks.getTaskList()) {
            java.util.List<String> descriptionWords = Arrays.asList(task.getDescription().split("\\s+"));
            final Set<String> wordsInDescription = new HashSet<>(descriptionWords);
            if (wordsInDescription.containsAll(commandDescription)) {
                duplicatedTask = task;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

}
