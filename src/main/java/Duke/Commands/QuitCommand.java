package Duke.Commands;

import Duke.Exceptions.DukeException;

public class QuitCommand extends UserCommand{

    @Override
    public String execute() throws DukeException {
        return "Bye ~ Hope you have enjoy your time ~~";
    }
}
