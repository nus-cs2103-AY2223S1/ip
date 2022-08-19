class InvalidEventException extends Exception {
    InvalidEventException() {
        super("You have an invalid deadline!\n");
    }
}