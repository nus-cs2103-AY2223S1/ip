package duke.commands;

import duke.exceptions.DukeException;

public interface AbstractCommand {

    public boolean validate();

    public void execute() throws DukeException;
}
