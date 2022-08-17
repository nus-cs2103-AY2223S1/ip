public class WelcomeRequest extends Request {
    private static final String WELCOME_MSG= "Hello! I'm Duke. \nWhat can I do for you?";

    @Override
    public void execute() {
        super.printResponse(WELCOME_MSG);
    }

}
