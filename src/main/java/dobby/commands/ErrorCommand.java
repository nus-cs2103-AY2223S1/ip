package dobby.commands;

import dobby.tasks.*;
import dobby.*;

public class ErrorCommand extends Command{
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        DobbyChat.unknown();
    }
}
