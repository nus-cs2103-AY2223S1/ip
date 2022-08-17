import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String DIVIDER = "-------------------------------------\n";
    private ArrayList<Task> tasks;
    public boolean end;

    public Duke() {
        this.tasks = new ArrayList<Task>();
        this.end = false;
    }

    private void run() {
        this.greet();
        while(!this.end) {
            this.handler();
        }
    }

    // greet method contains the greeting message
    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(DIVIDER + message + DIVIDER);
    }

    // handler method handles user input and outputs accordingly
    private void handler() {
        String command;
        Scanner sc = new Scanner(System.in);
        command = sc.next();

        switch(command) {
            case "list":
                list();
                break;
            case "todo":
            case "deadline":
            case "event":
                // deadline and event breaks if no input is entered after each command
                // tod0 creates an empty task if no input after command
                listAdd(command, sc.nextLine());
                break;
            case "mark":
                // breaks if no input is entered after mark, or input isn't int, or index out of range
                listToggle(sc.nextInt());
                break;
            case "bye":
                exit();
                break;
            default:
                confuse();
        }

        //if (!command.equals("bye")) { handler(); }

    }

    // sub methods associated with handler() for each user input case
    private void exit() {
        end = true;
        System.out.println(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }

    private static void confuse() {
        System.out.println(DIVIDER + "I'm sorry, I don't understand :(\n" + DIVIDER);
    }

    private void list() {
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

    private void listAdd(String type, String item) {
        Task currTask;
        String[] args;
        switch(type) {
            case "todo":
                currTask = new Todo(item);
                tasks.add(currTask);
                System.out.println(DIVIDER + "OK, I've added this todo:\n"
                        + "  " + currTask + "\n"
                        + "Number of tasks in list: " + tasks.size() + "\n"
                        + DIVIDER);
                break;
            case "deadline":
                args = item.split("/by");
                System.out.println(item);
                currTask = new Deadline(args[0], args[1]);
                tasks.add(currTask);
                System.out.println(DIVIDER + "OK, I've added this deadline:\n"
                        + "  " + currTask + "\n"
                        + "Number of tasks in list: " + tasks.size() + "\n"
                        + DIVIDER);
                break;
            case "event":
                args = item.split("/at");
                currTask = new Event(args[0], args[1]);
                tasks.add(currTask);
                System.out.println(DIVIDER + "OK, I've added this event:\n"
                        + "  " + currTask + "\n"
                        + "Number of tasks in list: " + tasks.size() + "\n"
                        + "\n" + DIVIDER);
                break;
        }
    }

    private void listToggle(int index) {
        Task currTask = tasks.get(index-1);
        if(currTask.completeToggle()) {
            System.out.println(DIVIDER + "Nice! I've marked this task as done:\n"
                    + currTask + "\n" + DIVIDER);
        } else {
            System.out.println(DIVIDER + "OK, I've marked this task as not done yet:\n"
                    + currTask + "\n" + DIVIDER);
        }
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
