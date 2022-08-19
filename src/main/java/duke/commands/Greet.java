package duke.commands;

import duke.enums.*;

public class Greet extends Display {

    public Greet() {

    }

    /**
     * Prints the greeting message
     */
    @Override
    public void execute() {
        wrapWithLines(Messages.GREET.toString(), Messages.LOGO.toString());
    }
}