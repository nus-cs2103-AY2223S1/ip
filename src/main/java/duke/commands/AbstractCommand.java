package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;

public interface AbstractCommand {

    public boolean validate();

    public void execute() throws DukeException, IOException;
}
