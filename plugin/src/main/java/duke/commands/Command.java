package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;

public interface Command {

    public boolean validate();

    public void execute() throws DukeException, IOException;
}
