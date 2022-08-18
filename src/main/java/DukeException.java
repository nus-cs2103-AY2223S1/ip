public class DukeException extends RuntimeException{

    private String exception = "";

    public DukeException(String e) {
        exception = e;
    }

    @Override
    public String toString() {
        return exception;
    }
}
