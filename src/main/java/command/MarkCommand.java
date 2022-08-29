package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Represent a mark commmand
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
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            int j = Integer.valueOf(cmd.substring(5).trim());
            tasklist.get(j).setMark(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
            storage.save();
        } catch (Exception e) {
            System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
        }
        
    }
    
}