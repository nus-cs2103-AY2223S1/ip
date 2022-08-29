package commands;

import duke.DukeException;
import duke.Ui;

public abstract class Command {
    public abstract String execute() throws DukeException;

}
