public class UwuChat {
    private static String horizontalLine ="\t-----------------------------------------------------";

    public static void greetingUsers() {
        System.out.println( horizontalLine +
                           "\n\tこんにちわ! " +
                           "\n\tわたしはううです <:" +
                           "\n\tよろしくおねがいします！" +
                           "\n\thello there, how can i be of service today? <3" +
                           "\n" + horizontalLine);
    }

    public static void leavingChat() {
        System.out.println(horizontalLine +
                           "\n\tgood work today! hope to see you again soon~" +
                           "\n" + horizontalLine);
    }

    public static void userCommands(String userCmd) {
        System.out.println(horizontalLine +
                            "\n\t" + userCmd +
                            "\n" + horizontalLine);
    }
}
