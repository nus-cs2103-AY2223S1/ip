public class MissingArgumentException extends DoemonException {
    /**
     * A string indicating the type of task that threw this exception.
     */
    private String task;
    /**
     * The flag that this type of task requires.
     */
    private String flag;

    /**
     * Constructor for a MissingArgumentException.
     * @param task a string indicating the type of task that threw this exception
     * @param flag the related flag that the type of task requires
     */
    public MissingArgumentException(String task, String flag) {
        this.task = task;
        this.flag = flag;
    }

    /**
     * Returns a string representation of the exception.
     * @return a string representing the exception
     */
    @Override
    public String toString() {
        return String.format("%s Looks like you may be missing a date/time for" +
                        "\n\tthis %s...Remember to use the %s flag!",
                super.toString(),
                this.task,
                this.flag);
    }
}
