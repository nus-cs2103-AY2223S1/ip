public class InvalidCommand extends Command {
    private static final String INVALID = "☹ OOPS!!! ";
    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public String action() {
        return INVALID + this.message + "\n";
    }
}
