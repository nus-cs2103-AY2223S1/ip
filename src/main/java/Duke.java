import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Set;
import java.util.Scanner;

/**
 * Creates a chatbot named Duke.
 */
public class Duke {

    public static void main(String[] args) {
        Duke program = new Duke();
        program.init();
    }

    /** List to store all tasks entered by the user. */
    private LinkedList<Task> lst;

    /** Set to store all known commands. */
    private Set<String> commandList = Set.of("bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete");

    /**
     * Starts and ends the Duke program.
     */
    private void init() {
        printIntro();
        program();
        exitProgram();
    }

    /**
     * Prints the hello introduction to Duke.
     */
    private void printIntro() {
        String logo = " _    _ ______ _      _      ____     ______ _____   ____  __  __    _____  _    _ _  ________ \n"
                + "| |  | |  ____| |    | |    / __ \\   |  ____|  __ \\ / __ \\|  \\/  |  |  __ \\| |  | | |/ /  ____|\n"
                + "| |__| | |__  | |    | |   | |  | |  | |__  | |__) | |  | | \\  / |  | |  | | |  | | ' /| |__   \n"
                + "|  __  |  __| | |    | |   | |  | |  |  __| |  _  /| |  | | |\\/| |  | |  | | |  | |  < |  __| \n"
                + "| |  | | |____| |____| |___| |__| |  | |    | | \\ \\| |__| | |  | |  | |__| | |__| | . \\| |____\n"
                + "|_|  |_|______|______|______\\____/   |_|    |_|  \\_\\\\____/|_|  |_|  |_____/ \\____/|_|\\_\\______|\n";
        System.out.println(logo);
        System.out.println("  How may I help you today?");
        newLine();
    }

    /**
     * Runs the chatbot, taking in inputs from the user.
     */
    private void program() {
        Scanner sc = new Scanner(System.in);
        this.lst = new LinkedList<>();
        String input = null, cmd = null, postCmd = null;
        String postSplit[];
        FileReader file = new FileReader();
        file.readFrom("./tasks.txt");
        file.updateList(lst);

        do {
            input = sc.nextLine();
            newLine();
            postSplit = input.split(" ");
            cmd = postSplit[0];
            try {
                if (!commandList.contains(cmd)) {
                    throw new UnknownCommandException(cmd);
                }
                postCmd = cmd.length() != input.length() ? input.substring(cmd.length() + 1) : "";
            } catch (UnknownCommandException e) {
                System.out.println(e);
                newLine();
                continue;
            }

            try {
                switch (cmd) {
                case "bye":
                    break;
                case "list":
                    printList();
                    break;
                case "todo":
                    handleTodo(postCmd);
                    break;
                case "deadline":
                    handleDeadline(postCmd);
                    break;
                case "event":
                    handleEvent(postCmd);
                    break;
                case "mark":
                case "unmark":
                case "delete":
                    handleMarkUnmarkDelete(cmd, postCmd);
                    break;
                }
            } catch (EmptyTaskException ete) {
                System.out.println(ete);
            } catch (InvalidArgumentException iae) {
                System.out.println(iae);
            } catch (EmptyDurationException ede) {
                System.out.println(ede);
            } catch (InvalidTaskNumberException itne) {
                System.out.println(itne);
            } finally {
                if (!cmd.equals("bye")) {
                    newLine();
                }
            }

        } while (!input.equals("bye"));

        file.writeFrom(this.lst);
    }

    /**
     * Handles the marking of a Task to be done or undone.
     *
     * @param cmd     The command entered by the user.
     * @param postCmd The rest of the input string after the command.
     * @throws InvalidTaskNumberException if the followup text after the command is not a valid integer.
     */
    private void handleMarkUnmarkDelete(String cmd, String postCmd) throws InvalidTaskNumberException {
        if (postCmd.equals("") || !isInteger(postCmd) || (Integer.parseInt(postCmd) - 1) < 0 ||
                (Integer.parseInt(postCmd) - 1) >= this.lst.size()) {
            throw new InvalidTaskNumberException("mark", postCmd);
        }
        int index = Integer.parseInt(postCmd) - 1;
        boolean change;
        if (cmd.equals("mark")) {
            change = this.lst.get(index).setDone();
            if (change) {
                System.out.println("  Nice! Task " + (index + 1) + " done!\n\t" + this.lst.get(index));
            } else {
                System.out.println("  Task " + (index + 1) + " is already done!\n\t" + this.lst.get(index));
            }
        }
        if (cmd.equals("unmark")) {
            change = this.lst.get(index).setUnDone();
            if (change) {
                System.out.println("  Ok! Task " + (index + 1) + " marked as not done!\n\t" + this.lst.get(index));
            } else {
                System.out.println("  Task " + (index + 1) + " is already not done!\n\t" + this.lst.get(index));
            }
        }
        if (cmd.equals("delete")) {
            Task removed = this.lst.get(index);
            removed.setUnDone();
            this.lst.remove(index);
            System.out.println("  Oki! The following task is removed:)\n\t" + removed);
        }
        printListCount();
    }

    /**
     * Checks if the text after mark/unmark/delete is an integer.
     *
     * @param input The text to be checked.
     * @return The boolean representing if the text is an integer.
     */
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Handles what to do with a "Todo" task.
     *
     * @param desc The description of the task.
     * @throws EmptyTaskException if the followup text after the command is empty.
     */
    private void handleTodo(String desc) throws EmptyTaskException {
        if (desc.trim().equals("")) {
            throw new EmptyTaskException("todo");
        }

        this.lst.add(new Todo(desc));
        System.out.println("  Added task todo: \n\t" + this.lst.get(this.lst.size() - 1));
        printListCount();
    }

    /**
     * Handles what to do with a "Deadline" task.
     *
     * @param desc The description of the task.
     * @throws EmptyTaskException       if the followup text after the command is empty.
     * @throws InvalidArgumentException if the followup text after the command and description is not "/by".
     * @throws EmptyDurationException   if the followup text after "/by" is empty.
     */
    private void handleDeadline(String desc) throws EmptyTaskException, InvalidArgumentException,
            EmptyDurationException {
        String[] split = desc.split("/by ");
        if (desc.trim().equals("") || split.length == 0 || split[0].equals("")) {
            throw new EmptyTaskException("deadline");
        }
        if (split[0].equals(desc)) {
            throw new InvalidArgumentException("deadline", "/by");
        }
        if (split.length == 1) {
            throw new EmptyDurationException("deadline", "/by");
        }
        this.lst.add(new Deadline(split[0].trim(), split[1]));
        System.out.println("  Added the task with deadline: \n\t" + this.lst.get(this.lst.size() - 1));
        printListCount();
    }

    /**
     * Handles what to do with a "Event" task.
     *
     * @param desc The description of the task.
     * @throws EmptyTaskException       if the followup text after the command is empty.
     * @throws InvalidArgumentException if the followup text after the command and description is not "/at".
     * @throws EmptyDurationException   if the followup text after "/at" is empty.
     */
    private void handleEvent(String desc) throws EmptyTaskException, InvalidArgumentException,
            EmptyDurationException {
        String[] split = desc.split("/at ");
        if (desc.trim().equals("") || split.length == 0 || split[0].equals("")) {
            throw new EmptyTaskException("event");
        }
        if (split[0].equals(desc)) {
            throw new InvalidArgumentException("event", "/at");
        }
        if (split.length == 1) {
            throw new EmptyDurationException("event", "/at");
        }
        this.lst.add(new Event(split[0].trim(), split[1]));
        System.out.println("  Added the event task: \n\t" + this.lst.get(this.lst.size() - 1));
        printListCount();
    }

    /**
     * Prints the number of current tasks, as well as how many are completed.
     */
    private void printListCount() {
        System.out.println("  You have " + this.lst.size() + " tasks currently, " + Task.totalDone + " are completed");
    }

    /**
     * Prints a list of all the tasks.
     */
    private void printList() {
        if (this.lst.size() == 0) {
            System.out.println("  List is empty!");
        } else {
            System.out.println("  List of tasks:");
            for (int i = 1; i <= this.lst.size(); i++) {
                System.out.println("\t" + i + ": " + this.lst.get(i - 1));
            }
            printListCount();
        }

    }

    /**
     * Prints the exit program text.
     */
    private void exitProgram() {
        System.out.println("Bye:( Hope to see you again soon!");
        newLine();
    }

    /**
     * Prints a new line.
     */
    private void newLine() {
        System.out.println("________________________________________________________________________" +
                "_______________________");
    }
}
