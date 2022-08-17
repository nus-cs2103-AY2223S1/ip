public class UnrecognisedCommandException extends TumuException {
    private String command;

    public UnrecognisedCommandException(String command) {
        super(command);
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("\t°՞(ᗒᗣᗕ)՞°Sorry, I don't " +
                "understand this command: %s", command);
    }
}
