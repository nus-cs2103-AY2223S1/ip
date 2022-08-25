public class DukeMessage {
    private static final String LINE = "____________________________________________________________";
    private static final String START_MESSAGE = " Hello! I'm Duke\n" + " What can I do for you?";
    private static final String END_MESSAGE = " Bye. Hope to see you again soon!";

    public static void sendMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void startMessage() {
        sendMessage(START_MESSAGE);
    }

    public static void endMessage() {
        sendMessage(END_MESSAGE);
    }
}
