public class BotResponse {
    public static void separationLine() {
        System.out.println("____________________________________________________________");
    }
    public static void welcome() {
        System.out.println("Hello, I'm Adam\n"
                        + "what can I do for you?");
        separationLine();
    }
    public static void bye() {
        System.out.println("Sorry to see you go, goodbye :(");
    }

    public static void addTaskLog(String description) {
        System.out.println("added: " + description);
        separationLine();
    }
}
