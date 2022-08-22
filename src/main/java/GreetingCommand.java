public class GreetingCommand extends Command {
    private static final String GREETING = "Hello! I am Duke\n" + "What can I do for you?\n";

    @Override
    public String action() {
        return GREETING;
    }
}
