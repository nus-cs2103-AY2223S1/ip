public class IncompatibleCommandException extends DrakeException {
    public IncompatibleCommandException() {
        super("These two go together like oil mixes with water");
    }

    public IncompatibleCommandException(String error) {
        super("These two go together like oil mixes with water. " + error);
    }
}
