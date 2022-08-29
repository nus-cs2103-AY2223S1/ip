package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Encapsulates a parser that reads the input and parse accordingly.
 */
public class Parser {

    private TaskList taskList;
    private Storage storage;

    /**
     * Creates a Parser object.
     * @param taskList TaskList object that stores tasks.
     * @param storage Storage object that stores and loads tasks.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Evaluates the user input and parse accordingly.
     * @param cmd String representation to be parsed.
     * @throws DukeException Throws a DukeException.
     */
    public void parse(String cmd) throws DukeException {
        String[] temp = cmd.split(" ");
        String mainCmd = temp[0];
        String[] subCmd = Arrays.copyOfRange(temp, 1, temp.length);

        switch (mainCmd) {
        case "list":
            this.parseList(subCmd);
            break;
        case "mark":
            this.parseMark(subCmd);
            break;
        case "unmark":
            this.parseUnmark(subCmd);
            break;
        case "todo":
            this.parseTodo(subCmd);
            break;
        case "deadline":
            this.parseDeadline(subCmd);
            break;
        case "event":
            this.parseEvent(subCmd);
            break;
        case "delete":
            this.parseDelete(subCmd);
            break;
        case "find":
            this.parseFind(subCmd);
            break;
        default:
            throw new InvalidDescriptionException();
        }
    }

    /**
     * Parses the list command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @throws InvalidDescriptionException Throws an invalidDescriptionException.
     */
    public void parseList(String[] subCmd) throws InvalidDescriptionException {
        if (subCmd.length != 0) {
            throw new InvalidDescriptionException();
        } else {
            this.taskList.listTasks();
        }
    }

    /**
     * Parses the mark command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @throws DukeException Throws a DukeException.
     */
    public void parseMark(String[] subCmd) throws DukeException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            this.taskList.getTask(Integer.parseInt(subCmd[0]) - 1).mark();
            this.storage.save(this.taskList);
        }
    }

    /**
     * Parses the unmark command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @throws DukeException Throws a DukeException.
     */
    public void parseUnmark(String[] subCmd) throws DukeException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            this.taskList.getTask(Integer.parseInt(subCmd[0]) - 1).unmark();
            this.storage.save(this.taskList);
        }
    }

    /**
     * Parses the todo command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @throws DukeException Throws a DukeException.
     */
    public void parseTodo(String[] subCmd) throws DukeException {

        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            Todo tmpTask = new Todo(tmp, false);
            System.out.println(tmpTask);
            this.taskList.addTask(tmpTask);

            this.storage.save(this.taskList);
        }
    }

    /**
     * Parses the deadline command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @throws DukeException Throws a DukeException.
     */
    public void parseDeadline(String[] subCmd) throws DukeException {
        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else if (!Arrays.asList(subCmd).contains("/by")) {
            throw new InvalidDescriptionException();
        } else {
            try {
                String[] tempSplit = tmp.split(" /by ");
                LocalDate tempDate = LocalDate.parse(tempSplit[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Deadline tmpTask = new Deadline(tempSplit[0], false, tempDate);

                this.taskList.addTask(tmpTask);
                this.storage.save(this.taskList);

            } catch (DateTimeParseException e) {
                throw new DukeException("Please change Date format to dd/mm/yyyy");
            }
        }
    }

    /**
     * Parses the event command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @throws DukeException Throws a DukeException.
     */
    public void parseEvent(String[] subCmd) throws DukeException {
        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            String[] tempSplit = tmp.split(" /at ");
            Event tmpTask = new Event(tempSplit[0], false, tempSplit[1]);

            this.taskList.addTask(tmpTask);
            this.storage.save(this.taskList);
        }
    }

    /**
     * Parses the delete command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @throws DukeException Throws a DukeException.
     */
    public void parseDelete(String[] subCmd) throws DukeException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            this.taskList.deleteTask(Integer.parseInt(subCmd[0]));
            this.storage.save(this.taskList);
        }
    }

    /**
     * Parses the find command.
     * @param subCmd String representation of the command.
     * @throws DukeException Throws DukeException.
     */
    public void parseFind(String[] subCmd) throws DukeException {
        String temp = String.join(" ", subCmd);
        if (temp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            this.taskList.findKeyword(temp);
        }
    }
}
