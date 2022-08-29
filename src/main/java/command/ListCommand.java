package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Represent a List command
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
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        System.out.println("You requested to view your schedule:");
        for (int j = 0; j < tasklist.size(); j++) {
            System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
        }
        
    }
    
}
