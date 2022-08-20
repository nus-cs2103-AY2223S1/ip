import java.util.ArrayList;
import java.util.Arrays;

public class DukeControl {
    /**
     * Arraylist contains a list of user's tasks
     */
    protected ArrayList<Task> arrayList;

    /**
     * Constructor for DukeControl
     * Initializes arrayList
     */
    public DukeControl() {
        this.arrayList = new ArrayList<>();
    }

    /**
     * Evaluates user's input according to a set of fixed commands
     * @param input User input represented by a String
     * @throws DukeException Throws a DukeException specific to this program
     */
    public void evaluate(String input) throws DukeException {
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
            System.out.println("Listing the tasks in your list...");
            for (int i = 0; i < this.arrayList.size(); i++) {
                System.out.println(String.format("\t%d. %s", i + 1, this.arrayList.get(i)));
            }
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
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
            throw new InvalidArgumentException();
        } else {
            this.arrayList.get(Integer.parseInt(commandArgs[0]) - 1).mark();
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
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
            throw new InvalidArgumentException();
        } else {
            this.arrayList.get(Integer.parseInt(commandArgs[0]) - 1).unmark();
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
            this.addTask(new Todo(title));
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
                this.addTask(new Deadline(title, deadline));
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
                this.addTask(new Event(title, time));
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
        } else if (Integer.parseInt(commandArgs[0]) <= 0 || Integer.parseInt(commandArgs[0]) > this.arrayList.size()) {
            throw new InvalidArgumentException();
        } else {
            this.deleteTask(Integer.parseInt(commandArgs[0]) - 1);
        }
    }

    /**
     * Adds a new Task to arraylist
     * @param newTask The new Task to be added
     */
    public void addTask(Task newTask) {
        this.arrayList.add(newTask);
        System.out.println(String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d task%s in the list.",
                newTask, this.arrayList.size(), this.arrayList.size() == 1 ? "" : "s"));
    }

    /**
     * Deletes a Task from arraylist
     * @param index The index of the Task to be deleted
     */
    public void deleteTask(int index) {
        Task deletedTask = this.arrayList.remove(index);
        System.out.println(String.format(
                "Noted. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                deletedTask, this.arrayList.size(), this.arrayList.size() == 1 ? "" : "s"));
    }
}
