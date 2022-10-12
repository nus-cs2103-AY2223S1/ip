package command;

import duke.Duke;
import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Command to end the Duke program.
 * Inherits from Command abstract class.
 */
public class ByeCommand extends Command {
    /**
     * Does Not do Anything
     * @param taskList
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public String deconstruct(ArrayList<DukeTask> taskList, Ui ui, Storage storage) throws InvalidFormatException {
        Duke.isterminated = true;
        return Ui.printExit();
    }
    
}
