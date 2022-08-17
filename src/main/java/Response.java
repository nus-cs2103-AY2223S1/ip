public abstract class Response {
    private static final String LINE = "____________________________________________________________";
    private static final String BORDER = LINE + "\n";

    public void printMessage(String message) {
        System.out.println(BORDER + message + BORDER);
    }

    public abstract void action() throws DukeException;
}
