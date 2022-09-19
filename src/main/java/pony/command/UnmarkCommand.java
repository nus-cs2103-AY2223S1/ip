package pony.command;

import pony.*;
import pony.task.Task;
import pony.TaskList;

/**
 * Command for unmarking a task.
 */
public class UnmarkCommand extends Command {
    private String commandDetails;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param commandDetails Details for the command.
     */
    public UnmarkCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes an Unmark command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int taskIndex = Parser.parseTaskIndex(commandDetails);
            Task task = tasks.getTask(taskIndex - 1);
            task.markAsNotDone();
            ui.printUnmarkedTask(task);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(":( OOPS!!! Please provide the correct details!!");
        }
    }
}
