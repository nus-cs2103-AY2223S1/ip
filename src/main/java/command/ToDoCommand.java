package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Represent a todo command
 */
public class ToDoCommand extends Command {
    private String cmd;

    public ToDoCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct a todo command based on cmd
     * adds it to tasklist
     * saves tasklist to task file
     */
    @Override
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            cmd = cmd.substring(5);
            if (cmd.isBlank()) {
                System.out.println("Oops, todo can't be empty");
            }
            DukeTask t = new DukeTask(cmd, false, 'T');
            tasklist.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
            storage.save();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Oops, todo can't be empty");
        } catch (Exception e) {
            System.out.println("...");
            System.out.println(e);
        }

    }
    
    
   
}
