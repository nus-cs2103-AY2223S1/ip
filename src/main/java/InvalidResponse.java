public class InvalidResponse extends Response{
    private static final String OOPS = "☹ OOPS!!! ";
    private String message;
    public InvalidResponse(String message) {
        this.message = message;
    }

    @Override
    public void action() {
        super.printMessage(OOPS + this.message + "\n");
    }
}
