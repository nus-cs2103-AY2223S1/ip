public class InvalidDateException extends Exception {
    @Override
    public String toString() {
        return "Please precede your date/time with a '/by' for a deadline and an '/at' for an event";
    }
}
