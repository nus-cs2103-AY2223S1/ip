public class GoodbyeRequest extends Request{
    private static final String goodbyeMsg = "Bye. Hope to see you again soon!";

    @Override
    public void execute() {
        super.printResponse(goodbyeMsg);
    }
}
