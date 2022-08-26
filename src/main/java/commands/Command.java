package commands;

import duke.DukeException;
import duke.Ui;

public abstract class Command {
    public abstract void execute() throws DukeException;

}
