public class DukeException extends Exception{

     DukeException(String errorMessage) {
        super("DUKEERROR: " + errorMessage);
    }

    DukeException(String command, String errorMessage) {
         super("DUKEERROR: " + command + " : " + errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}

