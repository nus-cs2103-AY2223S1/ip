package arc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Encapsulates a Parser that makes sense of the user input
 */
public class Parser {
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    /**
     * Constructor for Parser
     * @param storage Storage object that handles loading and saving of data
     * @param taskList TaskList object that stores all user tasks
     * @param ui UI object that handles user interface
     */
    public Parser(Storage storage, TaskList taskList, UI ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Evaluates user's input according to a set of fixed commands
     * @param input User input represented by a String
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void parse(String input) throws DukeException {
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
        case "find":
            this.parseFind(commandArgs);
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
            this.taskList.listTasks("");
        }
    }

    /**
     * Parses the mark command
     * @param commandArgs Array of Strings representing command arguments
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void parseMark(String[] commandArgs) throws DukeException {
        if (commandArgs.length != 1) {
            throw new InvalidArgumentException();
        } else if (Integer.parseInt(commandArgs[0]) <= 0
                || Integer.parseInt(commandArgs[0]) > this.taskList.numTasks()) {
            throw new InvalidArgumentException();
        } else {
            this.taskList.getTask(Integer.parseInt(commandArgs[0]) - 1).mark();
            this.storage.save(this.taskList);
        }
    }

    /**
     * Parses the unmark command
     * @param commandArgs Array of Strings representing command arguments
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void parseUnmark(String[] commandArgs) throws DukeException {
        if (commandArgs.length != 1) {
            throw new InvalidArgumentException();
        } else if (Integer.parseInt(commandArgs[0]) <= 0
                || Integer.parseInt(commandArgs[0]) > this.taskList.numTasks()) {
            throw new InvalidArgumentException();
        } else {
            this.taskList.getTask(Integer.parseInt(commandArgs[0]) - 1).unmark();
            this.storage.save(this.taskList);
        }
    }

    /**
     * Parses the todo command
     * @param commandArgs Array of Strings representing command arguments
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void parseTodo(String[] commandArgs) throws DukeException {
        String title = String.join(" ", commandArgs);

        if (title == "") {
            throw new EmptyTitleException();
        } else {
            this.taskList.addTask(new Todo(title, false));
            this.storage.save(this.taskList);
        }
    }

    /**
     * Parses the deadline command
     * @param commandArgs Array of Strings representing command arguments
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void parseDeadline(String[] commandArgs) throws DukeException {
        if (!Arrays.asList(commandArgs).contains("/by")) {
            throw new InvalidArgumentException();
        } else {
            int indexOfBy = Arrays.asList(commandArgs).indexOf("/by");
            String title = String.join(" ", Arrays.copyOfRange(commandArgs, 0, indexOfBy));
            String deadline = String.join(" ",
                    Arrays.copyOfRange(commandArgs, indexOfBy + 1, commandArgs.length));

            if (title == "") {
                throw new EmptyTitleException();
            } else if (deadline == "") {
                throw new InvalidArgumentException();
            } else {
                try {
                    LocalDate ldt = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    this.taskList.addTask(new Deadline(title, false, ldt));
                    this.storage.save(this.taskList);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Date format should be dd/mm/yyyy");
                }
            }
        }
    }

    /**
     * Parses the event command
     * @param commandArgs Array of Strings representing command arguments
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void parseEvent(String[] commandArgs) throws DukeException {
        if (!Arrays.asList(commandArgs).contains("/at")) {
            throw new InvalidArgumentException();
        } else {
            int indexOfBy = Arrays.asList(commandArgs).indexOf("/at");
            String title = String.join(" ", Arrays.copyOfRange(commandArgs, 0, indexOfBy));
            String time = String.join(" ",
                    Arrays.copyOfRange(commandArgs, indexOfBy + 1, commandArgs.length));

            if (title == "") {
                throw new EmptyTitleException();
            } else if (time == "") {
                throw new InvalidArgumentException();
            } else {
                this.taskList.addTask(new Event(title, false, time));
                this.storage.save(this.taskList);
            }
        }
    }

    /**
     * Parses the delete command
     * @param commandArgs Array of Strings representing command arguments
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void parseDelete(String[] commandArgs) throws DukeException {
        if (commandArgs.length != 1) {
            throw new InvalidArgumentException();
        } else if (Integer.parseInt(commandArgs[0]) <= 0
                || Integer.parseInt(commandArgs[0]) > this.taskList.numTasks()) {
            throw new InvalidArgumentException();
        } else {
            this.taskList.deleteTask(Integer.parseInt(commandArgs[0]) - 1);
            this.storage.save(this.taskList);
        }
    }

    public void parseFind(String[] commandArgs) throws InvalidArgumentException {
        if (commandArgs.length == 0) {
            throw new InvalidArgumentException();
        } else {
            String keyword = String.join(" ", commandArgs);
            this.taskList.listTasks(keyword);
        }
    }
}
