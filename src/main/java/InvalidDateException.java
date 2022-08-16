public class InvalidDateException extends Exception {
    /**
     * Exception for input with invalid date/time
     */
    @Override
    public String toString() {
        return "Please precede your date/time with a '/by' for a deadline and an '/at' for an event";
    }
}
