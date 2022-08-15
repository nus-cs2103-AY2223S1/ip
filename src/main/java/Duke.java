import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static String DIVIDER = "-------------------------------------\n";
    private static ArrayList<String> tasks = new ArrayList<String>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        handler();
    }

    // greet method contains the greeting message
    private static void greet() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(DIVIDER + message + DIVIDER);
    }

    // handler method handles user input and outputs accordingly
    private static void handler() {
        String command;
        String text;
        Scanner sc = new Scanner(System.in);
        command = sc.next();

        switch(command) {
            case "bye":
                exit();
                break;
            case "list":
                list();
                break;
            default:
                listAdd(command);
        }

        if (!command.equals("bye")) { handler(); }

    }

    // sub methods associated with handler() for each user input case
    private static void exit() {
        System.out.println(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }

    private static void list() {
        if (tasks.isEmpty()) {
            System.out.println(DIVIDER + "List is empty\n" + DIVIDER);
        } else {
            System.out.print(DIVIDER);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
            System.out.println(DIVIDER);
        }
    }

    private static void listAdd(String item) {
        tasks.add(item);
        System.out.println(DIVIDER + "added: " + item + "\n" + DIVIDER);
    }
}
