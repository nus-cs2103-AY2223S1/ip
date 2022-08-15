import java.util.Scanner;

public class Tumu {
    public static void main(String[] args) {
        greeting();
        response();
    }

    private static void response() {
        /**
         * Receives user response and replies accordingly.
         */

        Scanner sc = new Scanner(System.in);
        final String endChatBotMessage = "bye";
        String userInput;
        do {
            userInput = sc.next().toLowerCase();
            switch (userInput) {
                case endChatBotMessage:
                    goodbye();
                    break;
                default:
                    System.out.println(userInput);
            }

        } while (!userInput.equalsIgnoreCase(endChatBotMessage));
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
        String greetingMessage = "Hi! I am Tumu. Nice to meat you.\n" +
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
}
