public class InvalidCommandException extends Exception {
    /**
     * Exception for invalid command
     */
    @Override
    public String toString() {
        return "Invalid command. Please try again.";
    }
}
