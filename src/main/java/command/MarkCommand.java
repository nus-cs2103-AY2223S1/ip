package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Command to mark a Task as completed.
 * Inherits from Command abstract class.
 */
public class MarkCommand extends Command {
    private String cmd;

    public MarkCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct a mark command based on cmd
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            int j = Integer.valueOf(cmd.substring(5).trim());
            tasklist.get(j).setMark(true);
            StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");
            output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
            storage.save();
            return output.toString();
        } catch (Exception e) {
            return "Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e;
        }
        
    }
    
}