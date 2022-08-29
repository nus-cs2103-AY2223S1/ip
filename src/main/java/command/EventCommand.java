package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;
import task.DukeTaskEvent;

import java.util.ArrayList;

/**
 * Represent an Event command
 */
public class EventCommand extends Command {
    private String cmd;

    public EventCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct an event command based on cmd
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            int index = cmd.indexOf('/');
            if (index == -1) {
                throw new InvalidFormatException();
            }
            String task = cmd.substring(0, index - 1).trim();
            String time = "(" + cmd.substring(index + 1) + ')';
            DukeTask t = new DukeTaskEvent(task, false, 'E', time);
            tasklist.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
            storage.save();
        } catch (InvalidFormatException e) {
            System.out.println("Please format your Event request with a /{String}"); 
        } catch (Exception e) {
            System.out.println("Something went wrong in EventCommand" + e);
        }
    }
    
}
