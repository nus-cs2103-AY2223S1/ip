import java.util.Arrays;

public class DukeControl {
    private Storage storage;
    private TaskList tasklist;

    /**
     * Constructor for DukeControl
     * Initializes arrayList
     */
    public DukeControl(String filePath) {
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(this.storage.load());
    }

    /**
     * Evaluates user's input according to a set of fixed commands
     * @param input User input represented by a String
     * @throws DukeException Throws a DukeException specific to this program
     */
    public void run(String input) throws DukeException {
        String[] command = input.split(" ");
        String mainCommand = command[0];
        String[] commandArgs = Arrays.copyOfRange(command, 1, command.length);

        switch (mainCommand) {
            case "list":
                this.parseList(commandArgs);
                break;
            case "mark":
                this.parseMark(commandArgs);
                break;
            case "unmark":
                this.parseUnmark(commandArgs);
                break;
            case "todo":
                this.parseTodo(commandArgs);
                break;
            case "deadline":
                this.parseDeadline(commandArgs);
                break;
            case "event":
                this.parseEvent(commandArgs);
                break;
            case "delete":
                this.parseDelete(commandArgs);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Parses the list command
     * @param commandArgs Array of Strings representing command arguments
     * @throws InvalidArgumentException If additional arguments are entered
     */
    public void parseList(String[] commandArgs) throws InvalidArgumentException {
        if (commandArgs.length != 0) {
            throw new InvalidArgumentException();
        } else {
            this.tasklist.listTasks();
        }
    }

    /**
     * Parses the mark command
     * @param commandArgs Array of Strings representing command arguments
     * @throws InvalidArgumentException If additional arguments are entered or if index is out of bounds
     */
    public void parseMark(String[] commandArgs) throws InvalidArgumentException {
        if (commandArgs.length != 1) {
            throw new InvalidArgumentException();
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.tasklist.numTasks()) {
            throw new InvalidArgumentException();
        } else {
            this.tasklist.getTask(Integer.parseInt(commandArgs[0]) - 1).mark();
            this.storage.markTask(Integer.parseInt(commandArgs[0]) - 1);
        }
    }

    /**
     * Parses the unmark command
     * @param commandArgs Array of Strings representing command arguments
     * @throws InvalidArgumentException If additional arguments are entered or if index is out of bounds
     */
    public void parseUnmark(String[] commandArgs) throws InvalidArgumentException {
        if (commandArgs.length != 1) {
            throw new InvalidArgumentException();
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.tasklist.numTasks()) {
            throw new InvalidArgumentException();
        } else {
            this.tasklist.getTask(Integer.parseInt(commandArgs[0]) - 1).unmark();
            this.storage.unmarkTask(Integer.parseInt(commandArgs[0]) - 1);
        }
    }

    /**
     * Parses the todo command
     * @param commandArgs Array of Strings representing command arguments
     * @throws EmptyTitleException If input title is empty
     */
    public void parseTodo(String[] commandArgs) throws EmptyTitleException {
        String title = String.join(" ", commandArgs);

        if (title == "") {
            throw new EmptyTitleException();
        } else {
            this.tasklist.addTask(new Todo(title, false));
            this.storage.writeTask(new String[]{"T", "0", title});
        }
    }

    /**
     * Parses the deadline command
     * @param commandArgs Array of Strings representing command arguments
     * @throws DukeException If /by is not included or if input title or deadline is empty
     */
    public void parseDeadline(String[] commandArgs) throws DukeException {
        if (!Arrays.asList(commandArgs).contains("/by")) {
            throw new InvalidArgumentException();
        } else {
            int indexOfBy = Arrays.asList(commandArgs).indexOf("/by");
            String title = String.join(" ", Arrays.copyOfRange(commandArgs, 0, indexOfBy));
            String deadline = String.join(" ", Arrays.copyOfRange(commandArgs, indexOfBy + 1, commandArgs.length));

            if (title == "") {
                throw new EmptyTitleException();
            } else if (deadline == "") {
                throw new InvalidArgumentException();
            } else {
                this.tasklist.addTask(new Deadline(title, false, deadline));
                this.storage.writeTask(new String[]{"D", "0", title, deadline});
            }
        }
    }

    /**
     * Parses the event command
     * @param commandArgs Array of Strings representing command arguments
     * @throws DukeException If /at is not included or if input title or time is empty
     */
    public void parseEvent(String[] commandArgs) throws DukeException {
        if (!Arrays.asList(commandArgs).contains("/at")) {
            throw new InvalidArgumentException();
        } else {
            int indexOfBy = Arrays.asList(commandArgs).indexOf("/at");
            String title = String.join(" ", Arrays.copyOfRange(commandArgs, 0, indexOfBy));
            String time = String.join(" ", Arrays.copyOfRange(commandArgs, indexOfBy + 1, commandArgs.length));

            if (title == "") {
                throw new EmptyTitleException();
            } else if (time == "") {
                throw new InvalidArgumentException();
            } else {
                this.tasklist.addTask(new Event(title, false, time));
                this.storage.writeTask(new String[]{"E", "0", title, time});
            }
        }
    }

    /**
     * Parses the delete command
     * @param commandArgs Array of Strings representing command arguments
     * @throws InvalidArgumentException If additional arguments are entered or if index is out of bounds
     */
    public void parseDelete(String[] commandArgs) throws InvalidArgumentException {
        if (commandArgs.length != 1) {
            throw new InvalidArgumentException();
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.tasklist.numTasks()) {
            throw new InvalidArgumentException();
        } else {
            this.tasklist.deleteTask(Integer.parseInt(commandArgs[0]) - 1);
            this.storage.removeTask(Integer.parseInt(commandArgs[0]) - 1);
        }
    }
}
