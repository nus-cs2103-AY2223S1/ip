package zeus.command;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;

/**
 * Handles undoing commands.
 */
public class UndoCommand extends Command {

    /**
     * Constructor of UndoCommand class.
     */
    public UndoCommand() {
    }

    /**
     * Executes the command to undo a command.
     *
     * @param taskList List of tasks.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws ZeusException If last version reached and undo not possible.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        if (taskList.getHistorySize() <= 0) {
            throw new ZeusException("Last version reached: cannot undo further.");
        }
        taskList.undoLastVersion();
        ui.addMessageToResponse("Your last command has been undone!\n");
        ui.printList(taskList.getTaskList());
        storage.saveTasksToDisk(taskList.getTaskList());
    }
}
