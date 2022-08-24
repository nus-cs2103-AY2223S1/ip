public class DukeMessage {
    private static final String line = "____________________________________________________________";
    private static final String startMessage = " Hello! I'm Duke\n" + " What can I do for you?";
    private static final String endMessage = " Bye. Hope to see you again soon!";

    public static void sendMessage(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void startMessage() {
        sendMessage(startMessage);
    }

    public static void endMessage() {
        sendMessage(endMessage);
    }
}
