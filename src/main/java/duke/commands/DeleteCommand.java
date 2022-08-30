package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Command for deleting Tasks.
 */
public class DeleteCommand extends Command {
    private String remainingCommand;

    /**
     * DeleteCommand constructor.
     *
     * @param remainingCommand task number to delete.
     */
    public DeleteCommand(String remainingCommand) {
        this.remainingCommand = remainingCommand;
    }

    /**
     * Executes a Delete command.
     *
     * @param tasks TaskList that stores Tasks objects.
     * @param ui Ui that handles user interaction.
     * @param storage Storage that handles storing information on memory files.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(remainingCommand);
            String removedTask = tasks.deleteTask(taskNumber - 1);
            storage.deleteTaskFromDisk(taskNumber);
            return ui.printDeleteTask(removedTask, tasks.getTaskListSize());
        } catch (IndexOutOfBoundsException e) {
            return ("     OOPS!!! Please enter a valid task number.");
        } catch (IOException e) {
            return ("     " + e.getMessage());
        } catch (NumberFormatException e) {
            return ("     OOPS!!! Please enter a valid task number.");
        }
    }

    /**
     * Checks if program should exit.
     *
     * @return false as program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
