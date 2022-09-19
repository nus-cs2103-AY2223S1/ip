package pony.command;

import pony.Parser;
import pony.PonyException;
import pony.Storage;
import pony.TaskList;
import pony.Ui;

import pony.task.Task;

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
     * @return A command message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String message = "";
        try {
            int taskIndex = Parser.parseTaskIndex(commandDetails);
            Task task = tasks.getTask(taskIndex - 1);
            task.markAsNotDone();
            message = ui.printUnmarkedTask(task);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            message = e.getMessage();
        } catch (NumberFormatException e) {
            message = ":( OOPS!!! Please provide the correct details!!";
        }
        return message;
    }
}
