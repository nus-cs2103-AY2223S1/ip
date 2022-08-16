import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Tumu {
    private static List<Task> userTasks = new ArrayList<>();
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
            userInput = sc.nextLine().toLowerCase();
            printHorizontalLine();
            switch (userInput) {
                case endChatBotCMD:
                    goodbye();
                    break;
                case listUserTextCMD:
                    listTasks();
                    break;
                default:
                    addTask(userInput);
            }
            printHorizontalLine();

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

    private static void listTasks() {
        /**
         * Lists previous user texts in succession.
         */

        System.out.println("\tHere are your current tasks:");
        for (int i = 1; i <= userTasks.size(); i++) {
            Task task = userTasks.get(i - 1);
            String output = String.format("\t %d. [%s] %s",
                    i, task.isDone() ? "X" : " ", task.getTaskDescription());
            System.out.println(output);
        }
    }

    private static void addTask(String userInput) {
        /**
         * Adds userInput as a task.
         */

        System.out.println("\tI've added a task into your list:\n\t\t" + userInput);
        userTasks.add(new Task(userInput, false));
    }

    private static void printHorizontalLine() {
        /**
         * Prints the horizontal lines for chat-bot formatting.
         */

        System.out.println(horizontalLines);
    }
}
