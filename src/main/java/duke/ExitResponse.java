package duke;

public class ExitResponse extends Response{
    private static final String GOOD_BYE_MSG = "Bye. Hope to see you again soon!";

    public ExitResponse() {
        super(ExitResponse.GOOD_BYE_MSG);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
