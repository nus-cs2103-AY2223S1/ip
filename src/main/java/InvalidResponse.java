public class InvalidResponse extends Response{
    private static final String INVALID = "Invalid task!" + "\n";

    @Override
    public void action() {
        super.printMessage(INVALID);
    }
}
