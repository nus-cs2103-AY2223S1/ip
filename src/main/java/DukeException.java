class DukeException extends RuntimeException{
    private final String exception;

    public DukeException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return this.exception;
    }
}
