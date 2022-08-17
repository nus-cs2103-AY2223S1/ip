public class InvalidRequest extends Request {
    private static final String OOPS_MSG = "☹ OOPS! ";
    private String errorMsg;

    public InvalidRequest(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public void execute() {
        super.printResponse(OOPS_MSG + errorMsg);
    }
}
