public class DukeMissingInputException extends DukeException{
    private static final String MSG = "The additional argument for %s cannot be empty.";

    public DukeMissingInputException(String command){
        super(String.format(MSG, command));
    }
}
