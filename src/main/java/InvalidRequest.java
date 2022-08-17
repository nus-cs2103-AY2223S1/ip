public class InvalidRequest extends Request {
    private static final String INVALID_MSG = "Please enter a valid request / command!";

    @Override
    public void execute() {
        super.printResponse(INVALID_MSG);
    }
}
