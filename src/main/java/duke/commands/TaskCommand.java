package duke.commands;


import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command class for Task commands.
 */
public abstract class TaskCommand extends Command {
    /**
     * Executes a Task command.
     *
     * @param tasks TaskList that stores Tasks objects.
     * @param ui Ui that handles user interaction.
     * @param storage Storage that handles storing information on memory files.
     */
    @Override
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if program should exit.
     *
     * @return false as program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    public boolean taskDescriptionExists(TaskList tasks, String taskDescription) {
        return tasks.taskDescriptionExists(taskDescription);
    }
}
