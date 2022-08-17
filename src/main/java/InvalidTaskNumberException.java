public class InvalidTaskNumberException extends DoemonException {
    /**
     * The action being performed.
     */
    private String action;

    /**
     * Constructor for an InvalidTaskNumberException.
     * @param action the action being performed
     */
    public InvalidTaskNumberException(String action) {
        this.action = action;
    }

    /**
     * Returns a string representation of the exception.
     * @return a string representing the exception
     */
    @Override
    public String toString() {
        return String.format("%s Looks like you want to %s a task that doesn't" +
                        "\n\texist! Try again with a valid number.",
                super.toString(),
                this.action);
    }
}
