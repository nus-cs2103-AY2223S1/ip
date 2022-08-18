public class DukeException extends Exception {
    /**
     * Constructor for DukeException
     * @param message Message to be included when exception is thrown
     */
    public DukeException(String message) {
        super(message +
                "\naRCommands:\n" +
                "\tlist\n" +
                "\ttodo [title]\n" +
                "\tdeadline [title] /by [deadline]\n" +
                "\tevent [title] /at [time]\n" +
                "\tmark [index]\n" +
                "\tunmark [index]\n" +
                "\tdelete [index]");
    }
}
