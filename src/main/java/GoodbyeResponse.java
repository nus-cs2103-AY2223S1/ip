public class GoodbyeResponse extends Response{
    private static final String GOODBYE = "Bye. Hope to see you again soon!" + "\n";

    @Override
    public void action() {
        super.printMessage(GOODBYE);
    }
}
