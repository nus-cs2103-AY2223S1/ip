package sky.command;

import java.io.IOException;

import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.storage.History;
import sky.storage.Storage;
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
    public String execute(TaskList taskList, Storage storage, History history)
            throws TextNoMeaningException, IOException {
        try {
            int taskNum = generateTaskNumToDelete();
            Task task = taskList.getTask(taskNum);
            taskList.removeTask(task);
            storage.reWriteDataFile(taskList);
            history.addHistoryInTime(taskList);
            return generateResponse(task, taskList);
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

    private int generateTaskNumToDelete() {
        String taskNumInString = this.fullCommand.substring(7);
        // Minus one as arrayList is zero-indexed
        return Integer.parseInt(taskNumInString) - 1;
    }

    private String generateResponse(Task task, TaskList taskList) {
        return "Splendid. I've removed this task: \n"
                + "    " + task
                + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() <= 1 ? " task in the list." : " tasks in the list.");
    }
}
