import java.util.Scanner;

public class Duke {

    private static final String LINE = "    __________________________________________________________________________";
    private static final String INDENT = "     ";

    private static boolean isClosed = false;
    private static TaskList tasks = new TaskList();
    private static int counter = 0;

    public static void main(String[] args) {
        greetingMessage();
        Scanner sc = new Scanner(System.in);
        while (!isClosed) {
            try {
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
                            throw new DukeInvalidNumberFormatException("mark");
                        }
                    } else if (command.startsWith("unmark")) {
                        String[] splitted = command.split("\\s+");
                        Integer index;
                        try {
                            index = Integer.valueOf(splitted[1]);
                            tasks.markAsNotDone(index);
                        } catch (NumberFormatException err) {
                            throw new DukeInvalidNumberFormatException("unmark");
                        }
                    } else if (command.startsWith("todo")) {
                        tasks.addTodo(command);
                    } else if (command.startsWith("deadline")) {
                        tasks.addDeadline(command);
                    } else if (command.startsWith("event")) {
                        tasks.addEvent(command);
                    } else if (command.startsWith("delete")) {
                        String[] splitted = command.split("\\s+");
                        Integer index;
                        try {
                            index = Integer.valueOf(splitted[1]);
                            tasks.deleteTask(index);
                        } catch (NumberFormatException err) {
                            throw new DukeInvalidNumberFormatException("delete");
                        }
                    } else {
                        throw new DukeException("You have entered an invalid command");
                    }
                }
            } catch (DukeException ex) {
                drawLine();
                indentMessage(ex.getMessage());
                drawLine();
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