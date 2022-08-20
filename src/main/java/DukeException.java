public class DukeException extends Exception {

    String message;

    DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
