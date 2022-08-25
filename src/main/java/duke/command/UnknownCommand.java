package duke.command;

import duke.exception.DukeException;

public class UnknownCommand extends DukeException{
    public UnknownCommand() {
        super();
    }

    @Override
    public String toString() {
        return "I'm sorry, I don't understand what you mean.";
    }
}

