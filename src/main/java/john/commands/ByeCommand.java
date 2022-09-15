package john.commands;

/**
 * Represents an exit command, and terminates the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String FORMAT = "bye";

    /**
     * Returns true if the command is an instance of ByeCommand, false otherwise.
     *
     * @param command The command to check if it is ByeCommand.
     * @return True if command is an instance of ByeCommand, false otherwise.
     */
    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }

    /**
     * Returns a string representing the goodbye message, and exits the program.
     *
     * @return A string representing the goodbye message.
     */
    @Override
    public String execute() {
        setTimeout(() -> System.exit(0));
        return ui.showGoodbye();
    }

    // https://stackoverflow.com/questions/26311470/what-is-the-equivalent-of-javascript-settimeout-in-java
    private static void setTimeout(Runnable runnable) {
        new Thread(() -> {
            try {
                Thread.sleep(250);
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
