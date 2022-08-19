import java.util.Scanner;

public class Duke {
    TaskList tasks;

    /**
     * Constructor.
     */
    public Duke() {
        this.tasks = new TaskList();
    }

    /**
     * To greet the user.
     * @return A message used to greet the user.
     */
    public String greet() {
        return "Hello! I'm Duke\nWhat can I do for you? ^_^";
    }

    /**
     * To echo user's input.
     * @param message A message to echo.
     * @return The message user's provided.
     */
    public String echo(String message) {
        return "\t" + message;
    }

    /**
     * To exit the programme after users type "bye".
     * @return A goodbye message.
     */
    public String exit() {
        return "\tBye. Hope to see you again soon :D";
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        System.out.println(duke.greet());
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String command = scanner.next();
            String message = scanner.nextLine().trim();
            try {
                if (command.equals("bye")) {
                    System.out.println(duke.exit());
                    run = false;
                    scanner.close();
                } else if (command.equals("list")) {
                    System.out.println(duke.tasks.list());
                } else if (command.equals("mark")) {
                    if (message.length() == 0) {
                        throw new DukeException("Please mark using this format: mark <task-number>");
                    }
                    int taskNumber = Integer.parseInt(message);
                    System.out.println(duke.tasks.getTask(taskNumber - 1).markAsDone());
                } else if (command.equals("unmark")) {
                    if (message.length() == 0) {
                        throw new DukeException("Please unmark using this format: mark <task-number>");
                    }
                    int taskNumber = Integer.parseInt(message);
                    System.out.println(duke.tasks.getTask(taskNumber - 1).markAsNotDone());
                } else if (command.equals("todo")) {
                    if (message.length() == 0) {
                        throw new DukeException("Please add using the following format: todo <description>");
                    }
                    ToDo todo = new ToDo(message);
                    System.out.println(duke.tasks.add(todo));
                } else if (command.equals("deadline")) {
                    String[] info = message.split(" /by ");
                    if (info.length < 2) {
                        throw new DukeException("Please add using the following format: " +
                                "deadline <description> /by <date>");
                    }
                    String description = info[0];
                    String by = info[1];
                    Deadline deadline = new Deadline(description, by);
                    System.out.println(duke.tasks.add(deadline));
                } else if (command.equals("event")) {
                    String[] info = message.split(" /at ");
                    if (info.length < 2) {
                        throw new DukeException("Please add using the following format: " +
                                "event <description> /at <date>");
                    }
                    String description = info[0];
                    String at = info[1];
                    Event event = new Event(description, at);
                    System.out.println(duke.tasks.add(event));
                } else if (command.equals("delete")) {
                    if (message.length() == 0) {
                        throw new DukeException("I don't know which task to delete :(, " +
                                "please delete using the following format: delete <task-number>");
                    }
                    int taskNumber = Integer.parseInt(message);
                    if (taskNumber > duke.tasks.size()) {
                        throw new DukeException("The task number exceeds the number of tasks in the list!");
                    }
                    System.out.println(duke.tasks.delete(taskNumber));
                } else {
                    throw new DukeException("I'm sorry >< I don't know what this means :(");
                }
            } catch (DukeException e) {
                System.out.println("\t" + e.getMessage());
            }
        }
    }
}