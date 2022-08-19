import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final ArrayList<Task> db = new ArrayList<>(10);
    private final Scanner sc = new Scanner(System.in);

    /**
     * Welcome message that is printed upon starting the bot.
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
        } else if (userInput[0].equals("mark")) {
            return this.mark(userInput);
        } else if (userInput[0].equals("unmark")) {
            return this.unmark(userInput);
        } else {
            return this.add(s);
        }
    }

    /**
     * Lists out all Tasks stored in Duke.
     * @return 1 for successful execution
     */
    private int list() {
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
     * Classify the input task into To-do, Deadline or Event,
     * then adds the task to Duke's database.
     * @param s Input string by user
     * @return 1 on successful execution
     */
    private int add(String s) {
        Task task;

        // Event
        if (s.contains("/at")) {
            String[] tmp = s.split("/at");
            task = new Event(tmp[0].strip(), tmp[1].strip());
            db.add(task);
        // Deadline
        } else if (s.contains("/by")) {
            String[] tmp = s.split("/by");
            task = new Deadline(tmp[0].strip(), tmp[1].strip());
            db.add(task);
        // Normal To-Do
        } else {
            task = new Todo(s);
            db.add(task);
        }

        System.out.println("Got it. I added this task: ");
        System.out.println(task);
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
