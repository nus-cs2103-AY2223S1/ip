package duke.command;

import duke.DukeException;
import duke.storage.TaskRecords;
import duke.ui.BotUI;

public class DeleteCommand extends Command {
    private final String details;

    public DeleteCommand(String command, String details) {
        super(command);
        this.details = details;
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) throws DukeException {
        try {
            int taskIdx = Integer.parseInt(details) - 1;
            System.out.print(ui.successRemoved(taskList, taskList.delete(taskIdx)));
        } catch (NumberFormatException ex) {
            throw new DukeException(ui.invalidCheckFormat());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(ui.taskNotExist(taskList));
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
