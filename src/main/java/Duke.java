public class Duke {
    private final static String WELCOME =
            "Hello! I'm Duke\n" +
                    "What can I do for you?\n" +
            "use /at or /by";

    public static void main(String[] args) {
        System.out.println(WELCOME);
        CmdHandler cmdHandler = new CmdHandler();
        cmdHandler.handle();
    }
}
