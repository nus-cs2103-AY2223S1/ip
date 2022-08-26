class InvalidEventException extends Exception {
    InvalidEventException(String dateFormat) {
        super("You have an invalid event time!\nEvent times should be in the format: " + dateFormat + "\n");
    }
}