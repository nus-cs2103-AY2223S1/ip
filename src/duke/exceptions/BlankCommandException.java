package duke.exceptions;

public class BlankCommandException extends DukeException{

    public BlankCommandException() {
        super("BROTHER!\n YOU CANNOT GIVE ME BLANK :(");
    }
}
