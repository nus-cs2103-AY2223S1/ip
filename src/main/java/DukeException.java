public class DukeException extends Exception{

    DukeException(String errorMessage) {
        super("DUKEERROR: " + errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}

