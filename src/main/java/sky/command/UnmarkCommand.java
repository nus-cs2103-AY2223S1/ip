package sky.command;

import java.io.IOException;

import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.storage.History;
import sky.storage.Storage;
import sky.task.Task;

/**
 * The UnmarkCommand class deals with marking a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, History history)
            throws TextNoMeaningException, IOException {
        try {
            String taskNumInString = this.fullCommand.substring(7);
            // Minus one as arrayList is zero-indexed
            int taskNum = Integer.parseInt(taskNumInString) - 1;
            Task task = taskList.getTask(taskNum);
            task.markAsUndone();
            history.addHistoryInTime(taskList);
            storage.reWriteDataFile(taskList);
            String s = "Well, that's disappointing. I've marked this task as undone: \n"
                    + "    " + task;
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have either not entered any number to indicate which task "
                    + "I should unmark, or you entered an invalid task number.");
        } catch (NumberFormatException e) {
            throw new TextNoMeaningException("Are you new? Enter a number after typing unmark.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
