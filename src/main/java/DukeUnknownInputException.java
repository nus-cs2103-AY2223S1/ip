public class DukeUnknownInputException extends DukeException{
    private static final String MSG = "I'm sorry, I don't understand the command %s :(";

    public DukeUnknownInputException(String command){
        super(String.format(MSG, command));
    }
}
