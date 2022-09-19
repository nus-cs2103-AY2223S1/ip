package pony.command;

import pony.*;
import pony.task.Task;
import pony.TaskList;

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
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int taskIndex = Parser.parseTaskIndex(commandDetails);
            Task task = tasks.getTask(taskIndex - 1);
            tasks.deleteTask(taskIndex - 1);
            ui.printDeletedTask(task, tasks);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(":( OOPS!!! Please provide the correct details!!");
        }
    }
}
