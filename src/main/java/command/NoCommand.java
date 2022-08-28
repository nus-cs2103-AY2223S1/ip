package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

public class NoCommand extends Command {

    @Override
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        System.out.println("Sorry, I don't know what that means");
    }
    
}
