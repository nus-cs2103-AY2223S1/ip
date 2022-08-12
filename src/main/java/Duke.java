import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String BORDER = ">>========================="
            + "===========[**]============"
            + "========================<<";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void list() {
        System.out.println(BORDER);
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
            System.out.println();
            if (command.compareTo("bye") == 0) { // replace list of keywords with enums later
                quit();
                return;
            } else if (command.compareTo("list") == 0) {
                list();
            } else {
                addTask(command);
            }
        }

    }
}
