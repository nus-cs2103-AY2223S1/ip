public class GreetingResponse extends Response{
    private static final String GREETING = "Hello! I am Duke\n" + "What can I do for you?\n";

    @Override
    public void action() {
        super.printMessage(GREETING);
    }
}
