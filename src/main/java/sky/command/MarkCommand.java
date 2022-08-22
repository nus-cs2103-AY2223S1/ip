package sky.command;

import sky.Storage;
import sky.exception.TextNoMeaningException;
import sky.task.Task;
import sky.TaskList;
import sky.Ui;

/**
 * The MarkCommand class deals with marking a task as completed.
 */
public class MarkCommand extends Command {
    private String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TextNoMeaningException {
        try {
            String taskNumInString = this.fullCommand.substring(5);
            // Minus one as arrayList is zero-indexed
            int taskNum = Integer.parseInt(taskNumInString) - 1;
            Task task = taskList.getTask(taskNum);
            task.markAsDone();
            storage.reWriteDataFile(taskList);
            String s = "  Wow... who would have thought you had it in you... I've marked this task as done: \n" +
                    "    " + task;
            ui.displayText(s);
            return s;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  You have either not entered any number to indicate which task I should mark, \n" +
                    "  or you entered an invalid task number.");
        } catch (NumberFormatException e) {
            System.out.println("  Are you new? Enter a number after typing mark.");
        }
        throw new TextNoMeaningException("  Error executing MarkCommand.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}