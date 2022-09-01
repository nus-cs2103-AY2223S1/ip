package duke;


class DukeException extends RuntimeException{
    private final String exception;

    public DukeException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return this.exception;
    }

    @Override
    public String toString() {
        return this.exception;
    }
}
