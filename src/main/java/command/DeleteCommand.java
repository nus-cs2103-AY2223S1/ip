package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private String cmd;

    public DeleteCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            int j = Integer.valueOf(cmd.substring(7));
            System.out.println("Alight! I've deleted this task for you:");
            System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
            tasklist.remove(j);
            storage.save();

        } catch (Exception e) {
            //add proper exceptions handling later on
            System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
        }
        
    }
    
}
