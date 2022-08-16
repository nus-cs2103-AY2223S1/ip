public class NoDescriptionException extends Exception {
    /**
     * Exception for input with no description
     */
    @Override
    public String toString() {
        return "Your task has to have description :<";
    }
}
