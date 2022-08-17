import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class Tumu {
    private static List<Task> userTasks = new ArrayList<>();
    private static final String horizontalLines = "\t" + "_".repeat(60);

    private static final String END_CHAT_BOT_CMD = "bye";
    private static final String LIST_USER_TEXT_CMD = "list";
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";
    private static final String TODO_CMD = "todo";
    private static final String DEADLINE_CMD = "deadline";
    private static final String EVENT_CMD = "event";

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
            try {
                switch (command) {
                    case END_CHAT_BOT_CMD:
                        goodbye();
                        break;
                    case LIST_USER_TEXT_CMD:
                        listTasks();
                        break;
                    case MARK_CMD:
                        markTask(sc.nextInt());
                        break;
                    case UNMARK_CMD:
                        unmarkTask(sc.nextInt());
                        break;
                    case TODO_CMD:
                        addTodoTask(sc.nextLine().trim());
                        break;
                    case DEADLINE_CMD:
                        addDeadlineTask(sc.nextLine().trim());
                        break;
                    case EVENT_CMD:
                        addEventTask(sc.nextLine().trim());
                        break;
                    default:
                        //No commands are recognised.
                        throw new UnrecognisedCommandException(command.trim());
                }
            } catch (InputMismatchException e) {
                System.out.println("\tPlease (un)mark a task by its list position (must be an integer)!");
                sc.nextLine(); //clear buffer
            } catch (TumuException e) {
                System.out.println(e);
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

    private static void markTask(int oneIndexedNum) throws TumuException {
        /**
         * Mark the oneIndexedNumth Task in userTasks.
         */
        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) throw new MarkNoTaskException();
            else throw new MarkOutOfBoundsException(userTasks.size());
        } else {
            Task task = userTasks.get(oneIndexedNum - 1);
            task.markDone();
            System.out.println("\tAlright, I have marked this task as done:\n\t" + task);
        }
    }

    private static void unmarkTask(int oneIndexedNum) throws TumuException {
        /**
         * Unmark the oneIndexedNumth Task in userTasks.
         */

        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) throw new MarkNoTaskException();
            else throw new MarkOutOfBoundsException(userTasks.size());
        } else {
            Task task = userTasks.get(oneIndexedNum - 1);
            task.unmarkDone();
            System.out.println("\tAlright, I have unmarked this task:\n\t" + task);
        }
    }

    private static void addTodoTask(String userInput) throws TumuException {
        /**
         * Adds a todo task to list.
         */

        if (userInput.isBlank()) throw new TodoException();
        else taskTypeFormatting(new Todo(userInput));
    }

    private static void addDeadlineTask(String userInput) throws TumuException {
        /**
         * Adds a deadline to list.
         */

        //Check for "/by", if not available then prompt user to add timing.
        if (!userInput.contains("/by")) {
            throw new DENoTimingException("by");
        } else {
            //Parse the string. Make sure there is no multiple "/by" statements.
            String[] parse = userInput.replaceAll("\\s+", "").split("/by");
            if (parse.length > 2) throw new DETimingOverflowException();
            else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank())
                throw new DENoArgException();
            else taskTypeFormatting(new Deadline(parse[0], parse[1]));
        }
    }

    private static void addEventTask(String userInput) throws TumuException {
        /**
         * Adds an event to list.
         */

        //Check for "/at", if not available then prompt user to add timing.
        if (!userInput.contains("/at")) {
            throw new DENoTimingException("at");
        } else {
            //Parse the string. Make sure there is no multiple "/at" statements.
            String[] parse = userInput.replaceAll("\\s+", "").split("/at");
            if (parse.length > 2) throw new DETimingOverflowException();
            else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank())
                throw new DENoArgException();
            else taskTypeFormatting(new Event(parse[0], parse[1]));
        }
    }

    private static void taskTypeFormatting(Task task) {
        System.out.println("\tI've added a task into your list:\n\t\t" + task);
        userTasks.add(task);
        System.out.println(String.format("\tYou have %d task(s) in the list.", userTasks.size()));
    }

    private static void printHorizontalLine() {
        /**
         * Prints the horizontal lines for chat-bot formatting.
         */

        System.out.println(horizontalLines);
    }
}
