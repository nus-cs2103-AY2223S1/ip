package roger.storage;

import roger.commands.Command;
import roger.commands.CommandStub;
import roger.exceptions.RogerInvalidInputException;
import roger.ui.Parser;

/**
 * Testing stub for Parser class
 */
public class ParserStub extends Parser {

    public Command parse(String input) throws RogerInvalidInputException {
        return new CommandStub();
    }
}
