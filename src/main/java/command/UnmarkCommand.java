package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Command to unmark a Task as uncompleted. This is used normally when the user wants to unmark a marked task.
 * Inherits from Command abstract class.
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
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {

        int j = Integer.valueOf(cmd.substring(7).trim());
        assert j > tasklist.size(): "There is no such task number in your list";
        assert j < 0 : "please enter a valid number for me to unmark your task";
        tasklist.get(j).setMark(false);
        StringBuilder output = new StringBuilder("Got it. I've mark this task as not done:\n");
        output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
        storage.save();
        return output.toString();
        
    }
    
}
