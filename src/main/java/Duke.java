import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final ArrayList<Task> db = new ArrayList<>(10);
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome message upon starting the bot.
     */
    public void greet() {
        System.out.println("Hello from Duke!\n");
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
     * @return false if "bye" is input, true for any other instruction
     */
    public boolean processInput(String s) {
        String[] userInput = s.split(" ", 2);
        boolean goodbye = false;

        try {
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                goodbye = true;
            } else if (s.equals("list")) {
                this.list();
            } else if (userInput[0].equals("todo")) {
                this.addTodo(userInput);
            } else if (userInput[0].equals("deadline")) {
                this.addDeadline(userInput);
            } else if (userInput[0].equals("event")) {
                this.addEvent(userInput);
            } else if (userInput[0].equals("mark")) {
                this.mark(userInput);
            } else if (userInput[0].equals("unmark")) {
                this.unmark(userInput);
            } else if (userInput[0].equals("delete")) {
                this.delete(userInput);
            } else {
                throw new DukeException("Unrecognized command.");
            }
        } catch (DukeException err) {
            System.out.println(err);
        } finally {
            return goodbye;
        }
    }

    /**
     * Lists out all Tasks stored in Duke.
     */
    private void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < db.size(); i++) {
            System.out.println(i + 1 + ". " + db.get(i).toString());
        }
    }

    /**
     * Marks the input task as completed.
     * @param userInput Input task number
     */
    private void mark(String[] userInput) {
        Task tmp = db.get(Integer.parseInt(userInput[1]) - 1);
        tmp.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tmp);
    }

    /**
     * Marks the input task as incomplete.
     * @param userInput Input task number
     */
    private void unmark(String[] userInput) {
        Task tmp = db.get(Integer.parseInt(userInput[1]) - 1);
        tmp.setUndone();
        System.out.println("OK, I've marked this task as undone:");
        System.out.println(tmp);
    }

    /**
     * Adds a new To-Do task.
     * @param s Input task with description
     */
    private void addTodo(String[] s) throws DukeException {
        if (s.length < 2 || s[1].strip().equals("")) {
            throw new DukeException("Todo description is empty.");
        }

        Todo task = new Todo(s[1]);
        db.add(task);

        System.out.println("Got it. I added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.%n", db.size());
    }

    /**
     * Adds a new Event task.
     * @param s Input task with description
     */
    private void addEvent(String[] s) throws DukeException {
        if (s.length < 2 || s[1].strip().equals("")) {
            throw new DukeException("Event description is empty.");
        }

        String[] tmp = s[1].split("/at");
        if (tmp.length < 2) {
            throw new DukeException("Event description is missing the /at tag.");
        }
        Event task = new Event(tmp[0].strip(), tmp[1].strip());
        db.add(task);

        System.out.println("Got it. I added this event:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.%n", db.size());
    }

    /**
     * Adds a new Deadline task.
     * @param s Input task with description
     */
    private void addDeadline(String[] s) throws DukeException {
        if (s.length < 2 || s[1].strip().equals("")) {
            throw new DukeException("Deadline description is empty.");
        }

        String[] tmp = s[1].split("/by");
        if (tmp.length < 2) {
            throw new DukeException("Deadline description is missing the /by tag.");
        }
        Deadline task = new Deadline(tmp[0].strip(), tmp[1].strip());
        db.add(task);

        System.out.println("Got it. I added this deadline:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.%n", db.size());
    }

    private void delete(String[] userInput) {
        Task tmp = db.remove(Integer.parseInt(userInput[1]) - 1);
        System.out.println("OK, I've removed this task:");
        System.out.println("\t" + tmp);
        System.out.printf("Now you have %d tasks in the list.", db.size());
    }

    // Initializes and starts a Duke instance.
    public static void main(String[] args) {
        try {
            if (!Files.isDirectory(Paths.get("data/"))) {
                Files.createDirectories(Paths.get("data/"));
            }

            if (!Files.exists(Paths.get("data/duke.txt"))) {
                Files.createFile(Paths.get("data/duke.txt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Duke duke = new Duke();

        duke.greet();
        while (true) {
            String input = duke.getInput(duke.sc);
            if (duke.processInput(input)) {
                break;
            }
            System.out.println();
        }

        duke.sc.close();
    }
}
