package commands;

import exceptions.DukeException;

public interface AbstractCommand {

    public boolean validate();

    public void execute() throws DukeException;
}
