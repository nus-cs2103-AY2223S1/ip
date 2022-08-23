import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final ArrayList<Task> db = new ArrayList<>(10);
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints a welcome message upon starting the bot.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prompts the user for input.
     * @param sc Scanner object to read user input
     * @return User input for processing
     */
    public String getInput(Scanner sc) {
        System.out.println("What can I do for you?");
        return sc.nextLine();
    }

    /**
     * Checks the user input and performs the necessary instruction.
     * @param s User input
     * @return 0 if "bye" is input, 1 for successfully running any other instruction
     */
    public int processInput(String s) {
        String[] userInput = s.split(" ", 2);

        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again!");
            return 0;
        } else if (s.equals("list")) {
            return this.list();
        } else if (userInput[0].equals("todo")) {
            return this.addTodo(userInput[1]);
        } else if (userInput[0].equals("deadline")) {
            return this.addDeadline(userInput[1]);
        } else if (userInput[0].equals("event")) {
            return this.addEvent(userInput[1]);
        } else if (userInput[0].equals("mark")) {
            return this.mark(userInput);
        } else if (userInput[0].equals("unmark")) {
            return this.unmark(userInput);
        } else {
            return 1;
        }
    }

    /**
     * Lists out all Tasks stored in Duke.
     * @return 1 for successful execution
     */
    private int list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < db.size(); i++) {
            System.out.println(i + 1 + ". " + db.get(i).toString());
        }
        return 1;
    }

    /**
     * Marks the input task as completed.
     * @param userInput User input task number
     * @return 1 on successfully execution
     */
    private int mark(String[] userInput) {
        Task tmp = db.get(Integer.parseInt(userInput[1]) - 1);
        tmp.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tmp);
        return 1;
    }

    /**
     * Marks the input task as incomplete.
     * @param userInput User input task number
     * @return 1 on successfully execution
     */
    private int unmark(String[] userInput) {
        Task tmp = db.get(Integer.parseInt(userInput[1]) - 1);
        tmp.setUndone();
        System.out.println("OK, I've marked this task as undone:");
        System.out.println(tmp);
        return 1;
    }

    /**
     * Adds a new To-Do task.
     * @param s Input task description
     * @return 1 on successful execution
     */
    private int addTodo(String s) {
        Todo task = new Todo(s);
        db.add(task);

        System.out.println("Got it. I added this task: ");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.%n", db.size());
        return 1;
    }

    /**
     * Adds a new Event task.
     * @param s Input task description
     * @return 1 on successful execution
     */
    private int addEvent(String s) {
        String[] tmp = s.split("/at");
        Event task = new Event(tmp[0].strip(), tmp[1].strip());
        db.add(task);

        System.out.println("Got it. I added this event: ");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.%n", db.size());
        return 1;
    }

    /**
     * Adds a new Deadline task.
     * @param s Input task description
     * @return 1 on successful execution
     */
    private int addDeadline(String s) {
        String[] tmp = s.split("/by");
        Deadline task = new Deadline(tmp[0].strip(), tmp[1].strip());
        db.add(task);

        System.out.println("Got it. I added this deadline: ");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.%n", db.size());
        return 1;
    }

    // Initializes and starts a Duke instance.
    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.greet();
        while (true) {
            String input = duke.getInput(duke.sc);
            if (duke.processInput(input) == 0) {
                break;
            }
            System.out.println();
        }
    }
}
