package sky.command;

import sky.Storage;
import sky.exception.TextNoMeaningException;
import sky.task.Task;
import sky.TaskList;
import sky.Ui;

/**
 * The UnmarkCommand class deals with marking a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TextNoMeaningException {
        try {
            String taskNumInString = this.fullCommand.substring(7);
            // Minus one as arrayList is zero-indexed
            int taskNum = Integer.parseInt(taskNumInString) - 1;
            Task task = taskList.getTask(taskNum);
            task.markAsUndone();
            storage.reWriteDataFile(taskList);
            String s = "  Well, that's disappointing. I've marked this task as undone: \n" +
                    "    " + task;
            ui.displayText(s);
            return s;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  You have either not entered any number to indicate which task I should unmark, \n" +
                    "  or you entered an invalid task number.");
        } catch (NumberFormatException e) {
            System.out.println("  Are you new? Enter a number after typing unmark.");
        }
        throw new TextNoMeaningException("  Error executing UnmarkCommand.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
