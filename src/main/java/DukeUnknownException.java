public class DukeUnknownException extends DukeException{

    public DukeUnknownException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "  ---- \n  :( I have no idea what you are telling me to do.\n  ----";
    }
}