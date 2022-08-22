package sky.command;

import sky.Storage;
import sky.exception.TextNoMeaningException;
import sky.task.Task;
import sky.TaskList;
import sky.Ui;

/**
 * The DeleteCommand class deals with deleting a task from taskList.
 */
public class DeleteCommand extends Command {
    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TextNoMeaningException {
        try {
            String taskNumInString = this.fullCommand.substring(7);
            // Minus one as arrayList is zero-indexed
            int taskNum = Integer.parseInt(taskNumInString) - 1;
            Task task = taskList.getTask(taskNum);
            taskList.removeTask(task);
            storage.reWriteDataFile(taskList);
            String s = "  Splendid. I've removed this task: \n" +
                    "    " + task +
                    "\n  Now you have " + taskList.size() +
                    (taskList.size() <= 1 ? " task in the list.": " tasks in the list.");
            ui.displayText(s);
            return s;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  You have either not entered any number to indicate which task I should delete, \n" +
                    "  or you entered an invalid task number.");
        } catch (NumberFormatException e) {
            System.out.println("  Are you new? Enter a number after typing delete.");
        }
        throw new TextNoMeaningException("  Error executing DeleteCommand.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}