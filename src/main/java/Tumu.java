import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Tumu {
    private static List<String> userText = new ArrayList<>();
    private static final String horizontalLines = "\t" + "_".repeat(40);

    public static void main(String[] args) {
        greeting();
        response();
    }

    private static void response() {
        /**
         * Receives user response and replies accordingly.
         */

        Scanner sc = new Scanner(System.in);
        final String endChatBotCMD = "bye";
        final String listUserTextCMD = "list";
        String userInput;

        do {
            userInput = sc.next().toLowerCase();
            printHorizontalLines();
            switch (userInput) {
                case endChatBotCMD:
                    goodbye();
                    break;
                case listUserTextCMD:
                    listText();
                    break;
                default:
                    System.out.println("\tAdded: " + userInput);
                    userText.add(userInput);
            }
            printHorizontalLines();

        } while (!userInput.equalsIgnoreCase(endChatBotCMD));
    }

    private static void greeting() {
        /**
         * Greeting message to the user during chat-bot startup.
         */

        String logo = "" +
                "\t ▄▄▄▄▄▄▄ ▄▄   ▄▄ ▄▄   ▄▄ ▄▄   ▄▄ \n" +
                "\t█       █  █ █  █  █▄█  █  █ █  █\n" +
                "\t█▄     ▄█  █ █  █       █  █ █  █\n" +
                "\t  █   █ █  █▄█  █       █  █▄█  █\n" +
                "\t  █   █ █       █       █       █\n" +
                "\t  █   █ █       █ ██▄██ █       █\n" +
                "\t  █▄▄▄█ █▄▄▄▄▄▄▄█▄█   █▄█▄▄▄▄▄▄▄█\n\n";
        String greetingMessage = "\tHi! I am Tumu. Nice to meet you!\n" +
                "\tWhat is on your mind today?\n";

        System.out.println(logo + greetingMessage);
    }

    private static void goodbye() {
        /**
         * Says goodbye to the user.
         * User exits the chat-bot.
         */

        String goodbyeMessage = "\tGoodbye, and have a nice day ahead!\n";
        String smileyFace = "\t٩(ˊᗜˋ )و";
        System.out.println(goodbyeMessage + smileyFace);
    }

    private static void listText() {
        /**
         * Lists previous user texts in succession.
         */

        for (int i = 1; i <= userText.size(); i++) {
            System.out.println("\t" + i + ". " + userText.get(i - 1));
        }
    }

    private static void printHorizontalLines() {
        /**
         * Prints the horizontal lines for chat-bot formatting.
         */

        System.out.println(horizontalLines);
    }
}
