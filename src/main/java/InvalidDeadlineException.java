class InvalidDeadlineException extends Exception {
    InvalidDeadlineException(String dateFormat) {
        super("You have an invalid deadline!\nDeadlines should be in the format: " + dateFormat + "\n");
    }
}