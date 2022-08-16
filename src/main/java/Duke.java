import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String BORDER = ">>========================="
            + "===========[**]============"
            + "========================<<";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void list() {
        System.out.println(BORDER);
        System.out.println("  *rolls eyes*\n"
                + "  Do I have to?\n"
                + "  Fine. Here are your tasks:");
        taskList.forEach(t -> System.out.println("  " + (taskList.indexOf(t) + 1) + ". " + t));
        System.out.println(BORDER);
    }

    public static void addTask(String command) {
        Task task = new Task(command);
        taskList.add(task);
        System.out.println(BORDER + "\n  added: " + command + "\n" + BORDER);
    }

    public static void quit() {
        System.out.println(BORDER + "\n"
                + "  Good riddance, I say. With all due disrespect, leave me alone next time.\n"
                + BORDER);
    }

    public static void mark(String[] parsedCommand) {
        System.out.println(BORDER);
        if (parsedCommand.length != 2) {
            System.out.println("  Looks like someone doesn't know how to follow instructions...\n"
                    + "  Type \"mark <task number>\" to mark a task as complete.");
            System.out.println(BORDER);
        } else {
            try {
                int num = Integer.parseInt(parsedCommand[1]);
                taskList.get(num - 1).setDone(true);
                System.out.println("  " + taskList.get(num - 1));
                System.out.println(BORDER);
            } catch (NumberFormatException e) {
                System.out.println("  Do you need me to teach you what a number is?\n"
                        + "  Type \"mark <task number>\" to mark a task as complete.\n"
                        + BORDER);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  Brilliant. You've asked me to mark an imaginary task as complete.\n"
                        + BORDER);
            }
        }
    }

    public static void unmark(String[] parsedCommand) {
        System.out.println(BORDER);
        if (parsedCommand.length != 2) {
            System.out.println("  Looks like someone doesn't know how to follow instructions...\n"
                    + "  Type \"unmark <task number>\" to mark a task as incomplete.");
            System.out.println(BORDER);
        } else {
            try {
                int num = Integer.parseInt(parsedCommand[1]);
                taskList.get(num - 1).setDone(false);
                System.out.println("  " + taskList.get(num - 1));
                System.out.println(BORDER);
            } catch (NumberFormatException e) {
                System.err.println("  Do you need me to teach you what a number is?\n"
                        + "  Type \"unmark <task number>\" to mark a task as incomplete.\n"
                        + BORDER);
            } catch (IndexOutOfBoundsException e) {
                System.err.println("  Brilliant. You've asked me to mark an imaginary task as incomplete.\n"
                        + BORDER);
            }
        }
    }

    public static void main(String[] args) {

        String mort = "                               .---.        .-----------\n"
                + "                              /     \\  __  /    ------\n"
                + "                             / /     \\(  )/    -----\n"
                + "                            //////   ' \\/ `   ---\n"
                + "                           //// / // :    : ---\n"
                + "                          // /   /  /`    '--\n"
                + "                         //          //..\\\\\n"
                + ">>==================================UU[**]UU==================================<<\n"
                + "                                    '//||\\\\`\n"
                + "                                      ''``";

        System.out.println(mort);
        System.out.println("  Oh, it's you again...\n  Mort, begrudgingly at your service.");
        Scanner sc = new Scanner(System.in);
        System.out.println("  Hmph, what do you want now?\n" + BORDER);

        while (true) {
            System.out.println();
            String command = sc.nextLine();
            String[] parsed = command.split(" ");
            System.out.println();
            if (command.compareTo("bye") == 0) { // replace list of keywords with enums later
                quit();
                return;
            } else if (command.compareTo("list") == 0) {
                list();
            } else if (parsed[0].compareTo("mark") == 0) {
                mark(parsed);
            } else if (parsed[0].compareTo("unmark") == 0) {
                unmark(parsed);
            } else {
                addTask(command);
            }
        }

    }
}
