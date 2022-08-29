package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Represent an unmark command
 */
public class UnmarkCommand extends Command {
    private String cmd;

    public UnmarkCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct a deconstruct command based on cmd
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        int j = Integer.valueOf(cmd.substring(7).trim());
        tasklist.get(j).setMark(false);
        System.out.println("Got it. I've mark this task as not done:");
        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
        storage.save();
        
    }
    
}
