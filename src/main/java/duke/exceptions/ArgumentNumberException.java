package duke.exceptions;

public class ArgumentNumberException extends Exception{
    public ArgumentNumberException() {
        super("Please input the correct number of arguments for each command. Type 'help' to see all commands.");
    }
    public ArgumentNumberException(String msg){
        super(msg);
    }
}
