package pony.command;

import pony.Parser;
import pony.PonyException;
import pony.Storage;
import pony.TaskList;
import pony.Ui;

import pony.task.Task;

/**
 * Command for deleting a task.
 */
public class DeleteCommand extends Command {
    private String commandDetails;

    /**
     * Constructor for DeleteCommand.
     *
     * @param commandDetails Details for the command.
     */
    public DeleteCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes a Delete task command.
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
            tasks.deleteTask(taskIndex - 1);
            message = ui.printDeletedTask(task, tasks);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            message = e.getMessage();
        } catch (NumberFormatException e) {
            message = ":( OOPS!!! Please provide the correct details!!";
        }
        return message;
    }
}
