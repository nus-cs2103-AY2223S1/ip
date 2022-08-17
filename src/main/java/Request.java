public abstract class Request {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public void printResponse(String response) {
        System.out.println(Request.HORIZONTAL_LINE + "\n" + response + "\n" + Request.HORIZONTAL_LINE);
    }

    public abstract void execute();



}
