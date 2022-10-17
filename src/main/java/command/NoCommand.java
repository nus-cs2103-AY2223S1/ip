package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Represent a command that does not fall into any other categories.
 * It executes when the user inputs something that is not a command.
 */
public class NoCommand extends Command {
    /**
     * Does not do anything
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        return "Sorry, I don't know what that means";
    }
    
}
