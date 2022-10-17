package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Basic Command to add a Task to the TaskList.
 * Inherits from Command abstract class.
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
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            cmd = cmd.substring(5);
            if (cmd.isBlank()) {
                System.out.println("Oops, todo can't be empty");
            }
            DukeTask t = new DukeTask(cmd, false, 'T');
            tasklist.add(t);
            StringBuilder output = new StringBuilder("Got it. I've added this task:\n");
            output.append(String.format("List %d: ", tasklist.size() - 1) + t.toString());
            storage.save();
            return output.toString();
        } catch (StringIndexOutOfBoundsException e) {
            return"Oops, todo can't be empty";
        } catch (Exception e) {
            return "..." + e;
        }

    }
    
    
   
}
