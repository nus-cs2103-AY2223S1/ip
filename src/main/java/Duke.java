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

    public void changeStatus(String id, boolean mark) {
        int index = Integer.parseInt(id) - 1;
        if (mark) {
            System.out.println(this.tasks.getTask(index).markAsDone());
        } else {
            System.out.println(this.tasks.getTask(index).markAsNotDone());
        }
    }

    public void addTodo(String message) throws DukeException {
        if (message.length() == 0) {
            throw new DukeException("Please add using the following format: todo <description>");
        }
        ToDo todo = new ToDo(message);
        System.out.println(this.tasks.add(todo));
    }

    public void addDeadline(String message) throws DukeException {
        String[] info = message.split(" /by ");
        if (info.length < 2) {
            throw new DukeException("Please add using the following format: " +
                    "deadline <description> /by <date>");
        }
        String description = info[0];
        String by = info[1];
        Deadline deadline = new Deadline(description, by);
        System.out.println(this.tasks.add(deadline));
    }

    public void addEvent(String message) throws DukeException {
        String[] info = message.split(" /at ");
        if (info.length < 2) {
            throw new DukeException("Please add using the following format: " +
                    "event <description> /at <date>");
        }
        String description = info[0];
        String at = info[1];
        Event event = new Event(description, at);
        System.out.println(this.tasks.add(event));
    }

    public void delete(String message) throws DukeException {
        if (message.length() == 0) {
            throw new DukeException("I don't know which task to delete :(, " +
                    "please delete using the following format: delete <task-number>");
        }
        int taskNumber = Integer.parseInt(message);
        if (taskNumber > this.tasks.size()) {
            throw new DukeException("The task number exceeds the number of tasks in the list!");
        }
        System.out.println(this.tasks.delete(taskNumber));
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
                switch (command) {
                    case "bye":
                        System.out.println(duke.exit());
                        run = false;
                        scanner.close();
                        break;
                    case "list":
                        System.out.println(duke.tasks.list());
                        break;
                    case "mark":
                        if (message.length() == 0) {
                            throw new DukeException("Please mark using this format: mark <task-number>");
                        }
                        duke.changeStatus(message, true);
                        break;
                    case "unmark":
                        if (message.length() == 0) {
                            throw new DukeException("Please unmark using this format: mark <task-number>");
                        }
                        duke.changeStatus(message, false);
                        break;
                    case "todo":
                        duke.addTodo(message);
                        break;
                    case "deadline":
                        duke.addDeadline(message);
                        break;
                    case "event":
                        duke.addEvent(message);
                        break;
                    case "delete":
                        duke.delete(message);
                        break;
                    default:
                        throw new DukeException("I don't know what this means :(");
                }
            } catch (DukeException e) {
                System.out.println("\t" + e.getMessage());
            }
        }
    }
}