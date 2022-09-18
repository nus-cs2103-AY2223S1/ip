package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Class which handles deleting tasks.
 */
public class DeleteCommand extends Command {

    private String number;

    public DeleteCommand(String command) {
        number = command;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            int index = Integer.parseInt(number);
            String taskS = "tasks";
            if (tasks.listSize() - 1 == 1) {
                taskS = "task";
            }
            tasks.delete(index);
            storage.delete(index);
            return "Got it! Task " + index + " has been deleted from the list:\n  " + tasks.showTask(index)
                    + "\nYou have a total of " + (tasks.listSize() - 1) + " " + taskS + " in the list.";

        } catch (NumberFormatException e) {
            return "You need to provide a task's index to delete!";
        } catch (IndexOutOfBoundsException e) {
            return "You've given me an invalid task to delete!";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
