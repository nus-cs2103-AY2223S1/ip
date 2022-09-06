package sky.command;

import java.io.IOException;

import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.storage.History;
import sky.storage.Storage;

/**
 * The UndoCommand class deals with reverting state of task list to a point in history.
 */
public class UndoCommand extends Command {
    private String fullCommand;

    public UndoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, History history)
            throws TextNoMeaningException, IOException {
        try {
            String stepCountToRetractInString = this.fullCommand.substring(5);
            int stepCountToRetract = Integer.parseInt(stepCountToRetractInString);
            TaskList retrievedTaskList = history.revertStepsBack(stepCountToRetract);
            storage.reWriteDataFile(retrievedTaskList);
            String s = "Successfully reverted state back by " + (stepCountToRetract == 1
                    ? stepCountToRetract + " step." : stepCountToRetract + " steps.");
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have not entered any number to specify how many  "
                    + "steps do you want to retract by. Minimum is 1.");
        } catch (NumberFormatException e) {
            throw new TextNoMeaningException("Are you new? Enter a number after typing undo to specify "
                    + "how many steps do you want to retract by.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
