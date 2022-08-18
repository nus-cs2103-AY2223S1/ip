public class DukeWrongInputException extends DukeException{
    private static final String MSG = "The additional argument for %s is of the wrong data type.";

    public DukeWrongInputException(String command){
        super(String.format(MSG, command));
    }
}
