package duke.exceptions;

public class BlankCommandException extends DukeException{

    public BlankCommandException() {
        super("BROTHER!\nYOU CANNOT GIVE ME BLANK :(");
    }
}
