package sky.command;

import sky.Storage;
import sky.exception.TextNoMeaningException;
import sky.task.Task;
import sky.TaskList;

import java.io.IOException;

/**
 * The MarkCommand class deals with marking a task as completed.
 */
public class MarkCommand extends Command {
    private String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TextNoMeaningException, IOException {
        try {
            String taskNumInString = this.fullCommand.substring(5);
            // Minus one as arrayList is zero-indexed
            int taskNum = Integer.parseInt(taskNumInString) - 1;
            Task task = taskList.getTask(taskNum);
            task.markAsDone();
            storage.reWriteDataFile(taskList);
            String s = "Wow... who would have thought you had it in you... I've marked this task as done: \n" +
                    "    " + task;
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have either not entered any number to indicate which task I should mark, " +
                    "or you entered an invalid task number.");
        } catch (NumberFormatException e) {
            throw new TextNoMeaningException("Are you new? Enter a number after typing mark.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}