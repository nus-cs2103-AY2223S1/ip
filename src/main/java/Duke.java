import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String BORDER = ">>========================="
            + "===========[**]============"
            + "========================<<";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void list() {
        System.out.println("  *rolls eyes*\n"
                + "  Do I have to?\n"
                + "  Fine. Here are your tasks:");
        taskList.forEach(t -> System.out.println("  " + (taskList.indexOf(t) + 1) + ". " + t));
    }

    public static void addTask(String[] parsedCommand) {
        String taskType = parsedCommand[0];
        String[] parsed = parsedCommand[1].split(" /");
        Task task = new Task(parsed[0]);
        switch(taskType) {
            case "todo":
                task = new ToDo(parsed[0]);
                break;
            case "deadline":
                task = new Deadline(parsed[0], parsed[1]);
                break;
            case "event":
                task = new Event(parsed[0], parsed[1]);
                break;
        }
        taskList.add(task);
        System.out.println("  Seriously? Another one?\n" + "  Give me strength...\n"
                + "    " + task + "\n" + "  You have " + taskList.size() + " task"
                + (taskList.size() > 1 ? "s" : "") + ". Bummer.");
    }

    public static void quit() {
        System.out.println("  Good riddance, I say. With all due disrespect, leave me alone next time.");
    }

    public static void mark(String[] parsedCommand) {
        try {
            int num = Integer.parseInt(parsedCommand[1]);
            taskList.get(num - 1).setDone(true);
            System.out.println("    " + taskList.get(num - 1));
        } catch (NumberFormatException e) {
            System.out.println("  Do you need me to teach you what a number is?\n"
                    + "  Type \"mark <task number>\" to mark a task as complete.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Brilliant. You've asked me to mark an imaginary task as complete.");
        }
    }

    public static void unmark(String[] parsedCommand) {
        try {
            int num = Integer.parseInt(parsedCommand[1]);
            taskList.get(num - 1).setDone(false);
            System.out.println("    " + taskList.get(num - 1));
        } catch (NumberFormatException e) {
            System.out.println("  Do you need me to teach you what a number is?\n"
                    + "  Type \"unmark <task number>\" to mark a task as incomplete.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Brilliant. You've asked me to mark an imaginary task as incomplete.");
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
            String[] parsed = command.split(" ", 2);
            System.out.println();
            System.out.println(BORDER);
            if (command.compareTo("bye") == 0) { // replace list of keywords with enums later
                quit();
                System.out.println(BORDER);
                sc.close();
                return;
            } else if (command.compareTo("list") == 0) {
                list();
            } else if (parsed[0].compareTo("mark") == 0) {
                mark(parsed);
            } else if (parsed[0].compareTo("unmark") == 0) {
                unmark(parsed);
            } else if ((parsed[0].compareTo("todo") == 0 || parsed[0].compareTo("deadline") == 0)
                || parsed[0].compareTo("event") == 0) {
                addTask(parsed);
            } else {
                System.out.println("If you want my help, the least you could do is type a command I understand.");
            }
            System.out.println(BORDER);
        }

    }
}
