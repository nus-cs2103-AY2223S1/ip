import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Tumu {
    private static List<String> userText = new ArrayList<>();

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
            switch (userInput) {
                case endChatBotCMD:
                    goodbye();
                    break;
                case listUserTextCMD:
                    listText();
                    break;
                default:
                    System.out.println("Added: " + userInput);
                    userText.add(userInput);
            }

        } while (!userInput.equalsIgnoreCase(endChatBotCMD));
    }

    private static void greeting() {
        /**
         * Greeting message to the user during chat-bot startup.
         */

        String logo = "" +
                " ▄▄▄▄▄▄▄ ▄▄   ▄▄ ▄▄   ▄▄ ▄▄   ▄▄ \n" +
                "█       █  █ █  █  █▄█  █  █ █  █\n" +
                "█▄     ▄█  █ █  █       █  █ █  █\n" +
                "  █   █ █  █▄█  █       █  █▄█  █\n" +
                "  █   █ █       █       █       █\n" +
                "  █   █ █       █ ██▄██ █       █\n" +
                "  █▄▄▄█ █▄▄▄▄▄▄▄█▄█   █▄█▄▄▄▄▄▄▄█\n\n";
        String greetingMessage = "Hi! I am Tumu. Nice to meet you!\n" +
                "What is on your mind today?\n";

        System.out.println(logo + greetingMessage);
    }

    private static void goodbye() {
        /**
         * Says goodbye to the user.
         * User exits the chat-bot.
         */

        String goodbyeMessage = "Goodbye, and have a nice day ahead!\n";
        String smileyFace = "٩(ˊᗜˋ )و\n";
        System.out.println(goodbyeMessage + smileyFace);
    }

    private static void listText() {
        /**
         * Lists previous user texts in succession.
         */

        for (int i = 1; i <= userText.size(); i++) {
            System.out.println(i + ". " + userText.get(i - 1));
        }
    }
}
