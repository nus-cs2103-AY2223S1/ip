package sky.command;

import java.io.IOException;

import sky.Storage;
import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.task.Task;

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
            int taskNum = generateTaskNumToMark();
            Task task = taskList.getTask(taskNum);
            task.markAsDone();
            storage.reWriteDataFile(taskList);
            return generateResponse(task);
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have either not entered any number to indicate which task "
                    + "I should mark, or you entered an invalid task number.");
        } catch (NumberFormatException e) {
            throw new TextNoMeaningException("Are you new? Enter a number after typing mark.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private int generateTaskNumToMark() {
        String taskNumInString = this.fullCommand.substring(5);
        // Minus one as arrayList is zero-indexed
        return Integer.parseInt(taskNumInString) - 1;
    }

    private String generateResponse(Task task) {
        return "Well, that's disappointing. I've marked this task as undone: \n"
                + "    " + task;
    }
}
