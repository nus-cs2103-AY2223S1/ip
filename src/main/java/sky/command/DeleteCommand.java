package sky.command;

import java.io.IOException;

import sky.Storage;
import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.task.Task;

/**
 * The DeleteCommand class deals with deleting a task from taskList.
 */
public class DeleteCommand extends Command {
    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TextNoMeaningException, IOException {
        try {
            String taskNumInString = this.fullCommand.substring(7);
            // Minus one as arrayList is zero-indexed
            int taskNum = Integer.parseInt(taskNumInString) - 1;
            assert taskNum >= 0 : "taskNum should not be a negative number as it is used for"
                    + " array-indexing purposes.";
            Task task = taskList.getTask(taskNum);
            taskList.removeTask(task);
            storage.reWriteDataFile(taskList);
            String s = "Splendid. I've removed this task: \n"
                    + "    " + task
                    + "\nNow you have " + taskList.getSize()
                    + (taskList.getSize() <= 1 ? " task in the list." : " tasks in the list.");
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have either not entered any number to indicate which task I "
                    + "should delete, or you entered an invalid task number.");
        } catch (NumberFormatException e) {
            throw new TextNoMeaningException("Are you new? Enter a number after typing delete.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
