import java.util.Scanner;

public class Deku {
    private final String SEPARATOR = "____________________";
    private void introduction() {
        String logo ="    ____  ________ ____  __\n" +
                "   / __ \\/ ____/ //_/ / / /\n" +
                "  / / / / __/ / ,< / / / / \n" +
                " / /_/ / /___/ /| / /_/ /  \n" +
                "/_____/_____/_/ |_\\____/   \n";
        System.out.println(logo +
                "\nHello I'm DEKU\nHow may I help you today?\n" +
                SEPARATOR);
    }
    /*
    * Method to start the chat-bot
    */
    public void start() {
        this.introduction();
        boolean active = true;
        while (active) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                active = false;
                System.out.println(this.end());
                continue;
            }
            System.out.println(this.parseReply(userInput));
        }
    }
    private String end() {
        return this.parseReply("Bye! Until next time!");
    }
    private String parseReply(String input) {
        return SEPARATOR + "\n" + input + "\n" + SEPARATOR;
    }
    public static void main(String[] args) {
        Deku dekuBot = new Deku();
        dekuBot.start();
    }
}
