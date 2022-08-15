public class DukeException extends Exception{
    String command = "";

     DukeException(String errorMessage) {
        super(errorMessage);
    }

    DukeException(String command, String errorMessage) {
         super(command + " : " + errorMessage);
         this.command = command;
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
