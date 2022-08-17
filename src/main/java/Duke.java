import java.util.Scanner;

public class Duke {

    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENT = "     ";

    private static boolean isClosed = false;
    private static TaskList tasks = new TaskList();
    private static int counter = 0;

    public static void main(String[] args) {
        greetingMessage();
        Scanner sc = new Scanner(System.in);
        while (!isClosed) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                bye();
            } else if (command.equals("list")) {
                printList();
            } else {
                if (command.startsWith("mark")) {
                    String[] splitted = command.split("\\s+");
                    Integer index;
                    try {
                        index = Integer.valueOf(splitted[1]);
                        tasks.markAsDone(index);
                    } catch (NumberFormatException err) {
                        drawLine();
                        indentMessage("You have entered an invalid input.");
                        indentMessage("Please enter a number instead.");
                        indentMessage("Example: mark 1");
                        drawLine();
                    }
                } else if (command.startsWith("unmark")) {
                    String[] splitted = command.split("\\s+");
                    Integer index;
                    try {
                        index = Integer.valueOf(splitted[1]);
                        tasks.markAsNotDone(index);
                    } catch (NumberFormatException err) {
                        drawLine();
                        indentMessage("You have entered an invalid input.");
                        indentMessage("Please enter a number instead.");
                        indentMessage("Example: unmark 1");
                        drawLine();
                    }
                } else {
                    tasks.addToList(command);
                }
            }
        }
    }

    private static void greetingMessage() {
        drawLine();
        indentMessage("Hello! I'm Duke");
        indentMessage("What can I do for you?");
        drawLine();
    }

    private static void bye() {
        isClosed = true;
        drawLine();
        indentMessage("Bye, Hope to see you again soon!");
        drawLine();
    }

    private static void echo(String msg) {
        drawLine();
        indentMessage(msg);
        drawLine();
    }

    public static void indentMessage(String msg) {
        System.out.println(INDENT + msg);
    }

    public static void drawLine() {
        System.out.println(LINE);
    }

    public static void printList() {
        tasks.showList();
    }
}