package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Command to list all Tasks in the TaskList.
 * Inherits from Command abstract class.
 */
public class ListCommand extends Command {
    private String cmd;

    public ListCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct a list command based on cmd
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        StringBuilder output = new StringBuilder("You requested to view your schedule:\n");
        for (int j = 0; j < tasklist.size(); j++) {
            output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
            output.append("\n");
        }
        return output.toString();
    }
    
}
