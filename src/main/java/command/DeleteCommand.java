package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Command to delete a Task from the TaskList.
 * Inherits from Command abstract class.
 */
public class DeleteCommand extends Command {
    private String cmd;

    public DeleteCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct a delete command based on cmd
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            int j = Integer.valueOf(cmd.substring(7));
            StringBuilder output = new StringBuilder("Alight! I've deleted this task for you:\n");
            output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
            tasklist.remove(j);
            storage.save();
            return output.toString();

        } catch (Exception e) {
            return "Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e;
        }
        
    }
    
}
