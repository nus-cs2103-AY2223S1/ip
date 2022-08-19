package commands;

import enums.*;

public class Exit extends Display {
    public Exit() {

    }

    /**
     * Prints the exit message
     */
    @Override
    public void execute() {
        wrapWithLines(Messages.EXIT.toString());
    }
}