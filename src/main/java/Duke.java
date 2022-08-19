import java.util.Scanner;

public class Duke {
    final static private String GREETING = "Hello! I'm Duke\nWhat can I do for you? ^_^";
    final static private String EXIT = "\tBye. Hope to see you again soon :D";
    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }
    private TaskList tasks;

    /**
     * Constructor.
     */
    public Duke() {
        this.tasks = new TaskList();
    }

    /**
     * Echoes user's input.
     * @param message A message to echo.
     * @return The message user's provided.
     */
    public String echo(String message) {
        return "\t" + message;
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        System.out.println(Duke.GREETING);
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            try {
                try {
                    Command command = Command.valueOf(scanner.next().toUpperCase());
                    String message = scanner.nextLine().trim();
                    switch (command) {
                        case BYE:
                            System.out.println(Duke.EXIT);
                            run = false;
                            scanner.close();
                            break;
                        case LIST:
                            System.out.println(duke.tasks.list());
                            break;
                        case MARK:
                            if (message.length() == 0) {
                                throw new DukeException("Please mark using this format: mark <task-number>");
                            }
                            duke.tasks.changeStatus(message, true);
                            break;
                        case UNMARK:
                            if (message.length() == 0) {
                                throw new DukeException("Please unmark using this format: mark <task-number>");
                            }
                            duke.tasks.changeStatus(message, false);
                            break;
                        case TODO:
                            duke.tasks.addTodo(message);
                            break;
                        case DEADLINE:
                            duke.tasks.addDeadline(message);
                            break;
                        case EVENT:
                            duke.tasks.addEvent(message);
                            break;
                        case DELETE:
                            duke.tasks.delete(message);
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    throw new DukeException("I don't know what this means :(");
                }
            } catch (DukeException e) {
                System.out.println("\t" + e.getMessage());
            }
        }
    }
}