import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class Tumu {
    private static List<Task> userTasks = new ArrayList<>();
    private static final String horizontalLines = "\t" + "_".repeat(40);

    private static final String END_CHAT_BOT_CMD = "bye";
    private static final String LIST_USER_TEXT_CMD = "list";
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";

    public static void main(String[] args) {
        greeting();
        response();
    }

    private static void response() {
        /**
         * Receives user response and replies accordingly.
         */

        Scanner sc = new Scanner(System.in);
        String command;

        do {
            command = sc.next().toLowerCase();

            printHorizontalLine();
            switch (command) {
                case END_CHAT_BOT_CMD:
                    goodbye();
                    break;
                case LIST_USER_TEXT_CMD:
                    listTasks();
                    break;
                case MARK_CMD:
                    try {
                        markTask(sc.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.println("\tPlease mark a task by its list position (must be an integer)!");
                        sc.nextLine(); //clear buffer
                    }
                    break;
                case UNMARK_CMD:
                    try {
                        unmarkTask(sc.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.println("\tPlease unmark a task by its list position (must be an integer)!");
                        sc.nextLine(); //clear buffer
                    }
                    break;
                default:
                    addTask(command);
            }
            printHorizontalLine();

        } while (!command.equalsIgnoreCase(END_CHAT_BOT_CMD));
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
            String output = String.format("\t %d. %s", i, task);
            System.out.println(output);
        }
    }

    private static void markTask(int oneIndexedNum) {
        /**
         * Mark the oneIndexedNumth Task in userTasks.
         */
        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) System.out.println("\tNo tasks currently available. Add a task before marking!");
            else System.out.println("\tSpecified index is out of bounds, please key a value from 1 to " + userTasks.size());
        } else {
            Task task = userTasks.get(oneIndexedNum - 1);
            task.markDone();
            System.out.println("\tAlright, I have marked this task as done: \n\t" + task);
        }
    }

    private static void unmarkTask(int oneIndexedNum) {
        /**
         * Unmark the oneIndexedNumth Task in userTasks.
         */

        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) System.out.println("\tNo tasks currently available. Add a task before unmarking!");
            else System.out.println("\tSpecified index is out of bounds, please key a value from 1 to " + userTasks.size());
        } else {
            Task task = userTasks.get(oneIndexedNum - 1);
            task.unmarkDone();
            System.out.println("\tAlright, I have unmarked this task: \n\t" + task);
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
