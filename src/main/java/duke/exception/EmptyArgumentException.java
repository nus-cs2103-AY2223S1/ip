package duke.exception;

import duke.command.Commands;

public class EmptyArgumentException extends DukeException {

    public EmptyArgumentException(Commands argument) {
        super(argument.name());
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a " + super.toString() +" cannot be empty.";
    }


}
