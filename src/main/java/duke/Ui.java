package duke;

public class Ui {

    private static final String LINE_STR = "-".repeat(50);
    private final String chatBotName;

    public Ui(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    public void welcomeUser() {
        replyUser(
            String.join("\n", String.format("Hi I'm %s", chatBotName), "What can I do for you?"));
    }

    public void replyUser(String response) {
        System.out.printf("\t%s\n", LINE_STR);
        System.out.printf("\t%s\n", response.replaceAll("\\n", "\n\t"));
        System.out.printf("\t%s\n", LINE_STR);
    }

    public void raiseError(String errorMessage) {
        replyUser(String.format("X %s", errorMessage));
    }
}
