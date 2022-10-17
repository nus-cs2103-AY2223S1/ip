package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;
import task.DukeTaskEvent;

import java.util.ArrayList;

/**
 * Command to set any constrain for a Task from the TaskList.
 * Inherits from Command abstract class.
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
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            int index = cmd.indexOf('/');
            if (index == -1) {
                throw new InvalidFormatException();
            }
            String task = cmd.substring(0, index - 1).trim();
            String time = "(" + cmd.substring(index + 1) + ')';
            DukeTask t = new DukeTaskEvent(task, false, 'E', time);
            tasklist.add(t);
            StringBuilder output = new StringBuilder("Got it. I've added this task:\n");
            output.append(String.format("List %d: ", tasklist.size() - 1) + t.toString());
            storage.save();
            return output.toString();
        } catch (InvalidFormatException e) {
            return "Please format your Event request with a /{String}";
        } catch (Exception e) {
            return "Something went wrong in EventCommand" + e;
        }
    }
    
}
