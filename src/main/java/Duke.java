import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String DIVIDER = "-------------------------------------\n";
    private ArrayList<Task> tasks;
    private boolean end;

    public Duke() {
        this.tasks = new ArrayList<Task>();
        this.end = false;
    }

    private void run() {
        String command;
        Scanner sc = new Scanner(System.in);

        this.greet();
        while(!this.end) {
            command = sc.nextLine();
            try {
                this.handler(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
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
    private void handler(String input) throws DukeException {
        String[] args = input.split(" ", 2);

        switch(args[0]) {
            case "list":
                list();
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    listAdd(args[0], args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "mark":
                try {
                    listToggle(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "bye":
                exit();
                break;
            default:
                throw new DukeUnknownInputException(args[0]);
        }
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

    // atodo, deadline and event breaks if no input is entered after each command (1 for atodo, 2 for others)
    // tod0 creates an empty task if no input after command (unresolved)
    private void listAdd(String type, String item) throws DukeException {
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
                try{
                    currTask = new Deadline(args[0], args[1]);
                    tasks.add(currTask);
                    System.out.println(DIVIDER + "OK, I've added this deadline:\n"
                            + "  " + currTask + "\n"
                            + "Number of tasks in list: " + tasks.size() + "\n"
                            + DIVIDER);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(type);
                }
                break;
            case "event":
                args = item.split("/at");
                try{
                    currTask = new Event(args[0], args[1]);
                    tasks.add(currTask);
                    System.out.println(DIVIDER + "OK, I've added this event:\n"
                            + "  " + currTask + "\n"
                            + "Number of tasks in list: " + tasks.size() + "\n"
                            + "\n" + DIVIDER);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(type);
                }
                break;
        }
    }

    // breaks if no input is entered after mark, or input isn't int, or index out of range
    private void listToggle(String indexString) throws DukeException{
        int index = 0;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("mark");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOOBException(index);
        }
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
