import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String DIVIDER = "-------------------------------------\n";
    private static ArrayList<Task> TASKS = new ArrayList<Task>();

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
        Scanner sc = new Scanner(System.in);
        command = sc.next();

        switch(command) {
            case "list":
                list();
                break;
            case "mark":
                // breaks if no input is entered after mark
                listToggle(sc.nextInt());
                break;
            case "bye":
                exit();
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
        if (TASKS.isEmpty()) {
            System.out.println(DIVIDER + "List is empty\n" + DIVIDER);
        } else {
            System.out.print(DIVIDER);
            for (int i = 0; i < TASKS.size(); i++) {
                System.out.println((i+1) + ". " + TASKS.get(i));
            }
            System.out.println(DIVIDER);
        }
    }

    private static void listAdd(String item) {
        TASKS.add(new Task(item));
        System.out.println(DIVIDER + "added: " + item + "\n" + DIVIDER);
    }

    private static void listToggle(int index) {
        Task currTask = TASKS.get(index-1);
        if(currTask.completeToggle()) {
            System.out.println(DIVIDER + "Nice! I've marked this task as done:\n"
                    + currTask + "\n" + DIVIDER);
        } else {
            System.out.println(DIVIDER + "OK, I've marked this task as not done yet:\n"
                    + currTask + "\n" + DIVIDER);
        }
    }
}
