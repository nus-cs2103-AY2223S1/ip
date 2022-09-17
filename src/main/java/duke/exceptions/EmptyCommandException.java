package duke.exceptions;

public class EmptyCommandException extends Exception{
    public EmptyCommandException() {
        super("Duke Aemon detected no command from the user. Use the `help` command to see all commands supported.");
    }
}
