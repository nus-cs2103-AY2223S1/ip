package Duke.Commands;

import Duke.Exceptions.DukeException;

/**
 * Class that denotes the command of quitting the program.
 */
public class QuitCommand extends UserCommand{
    @Override
    public String execute() throws DukeException {
        return "Bye ~ Hope you have enjoy your time ~~";
    }
}
