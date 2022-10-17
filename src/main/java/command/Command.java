package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Abstract class representing commands that Duke program can run.
 * Each and every other command will inherit from Command super class.
 */
public abstract class Command {
    /**
     * Executes the Command
     * @throws InvalidFormatException
     */
    public abstract String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException;
}
