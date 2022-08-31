package exceptions;

public class InvalidDescriptionException extends DukeException {

    private final String description;
    private final static String INDEX = "index";
    private final static String EVENT = "event";
    private final static String DEADLINE = "deadline";

    public InvalidDescriptionException(String description) {
        this.description = description;
    }

    @Override
    public String getMessage() {
        String s;
        switch (description) {
        case INDEX:
            s = "Description must be an integer!";
            break;
        case EVENT:
            s = "Description of EVENT must be in the format (EVENT /at YYYY/MM/DD TT:TT)!";
            break;
        case DEADLINE:
            s = "Description of DEADLINE must be in the format (EVENT /by YYYY/MM/DD TT:TT)!";
            break;
        default:
            s = "";
        }
        return s;
    }

}

