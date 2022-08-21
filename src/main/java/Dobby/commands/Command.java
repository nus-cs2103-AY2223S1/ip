package dobby.commands;

import dobby.tasks.*;
import dobby.*;

import java.io.IOException;

public abstract class Command {
    protected boolean isBye;

    public boolean isBye() {
        return isBye;
    }
    public abstract void execute(DobbyList dl, UserInput ui) throws IOException;
}