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
     * @return Returns a string representing the command.
     * @throws DukeException Throws a DukeException.
     */
    public String parse(String cmd) throws DukeException {
        String[] temp = cmd.split(" ");
        String mainCmd = temp[0];
        String[] subCmd = Arrays.copyOfRange(temp, 1, temp.length);

        switch (mainCmd) {
        case "list":
            return this.parseList(subCmd);
        case "mark":
            return this.parseMark(subCmd);
        case "unmark":
            return this.parseUnmark(subCmd);
        case "todo":
            return this.parseTodo(subCmd);
        case "deadline":
            return this.parseDeadline(subCmd);
        case "event":
            return this.parseEvent(subCmd);
        case "delete":
            return this.parseDelete(subCmd);
        case "find":
            return this.parseFind(subCmd);
        default:
            throw new InvalidDescriptionException();
        }
    }

    /**
     * Parses the list command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @return A string representing the list command.
     * @throws InvalidDescriptionException Throws an invalidDescriptionException.
     */
    public String parseList(String[] subCmd) throws InvalidDescriptionException {
        if (subCmd.length != 0) {
            throw new InvalidDescriptionException();
        } else {
            return this.taskList.listTasks();
        }
    }

    /**
     * Parses the mark command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @return A string representing the mark command.
     * @throws DukeException Throws a DukeException.
     */
    public String parseMark(String[] subCmd) throws DukeException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            String markMessage = this.taskList.getTask(Integer.parseInt(subCmd[0]) - 1).mark();
            this.storage.save(this.taskList);
            return markMessage;

        }
    }

    /**
     * Parses the unmark command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @return A string representing the unmark command.
     * @throws DukeException Throws a DukeException.
     */
    public String parseUnmark(String[] subCmd) throws DukeException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            String unMarkMessage = this.taskList.getTask(Integer.parseInt(subCmd[0]) - 1).unmark();
            this.storage.save(this.taskList);
            return unMarkMessage;
        }
    }

    /**
     * Parses the todo command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @return A string representing the todo command.
     * @throws DukeException Throws a DukeException.
     */
    public String parseTodo(String[] subCmd) throws DukeException {

        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            String[] tempSplit = tmp.split(" /p ");

            Todo tmpTask = new Todo(tempSplit[0], false, PriorityLevel.getPriorityString(tempSplit[1]));
            String toDoMessage = this.taskList.addTask(tmpTask);
            this.storage.save(this.taskList);
            return toDoMessage;
        }
    }

    /**
     * Parses the deadline command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @return A string representing the deadline command.
     * @throws DukeException Throws a DukeException.
     */
    public String parseDeadline(String[] subCmd) throws DukeException {
        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else if (!Arrays.asList(subCmd).contains("/by")) {
            throw new InvalidDescriptionException();
        } else {
            try {
                String[] tempSplit = tmp.split(" /by ");
                String[] prioritySplit = tempSplit[1].split(" /p ");
                LocalDate tempDate = LocalDate.parse(prioritySplit[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Deadline tmpTask = new Deadline(tempSplit[0], false, tempDate, PriorityLevel.getPriorityString(prioritySplit[1]));
                String DeadlineMessage = this.taskList.addTask(tmpTask);
                this.storage.save(this.taskList);
                return DeadlineMessage;

            } catch (DateTimeParseException e) {
                throw new DukeException("Please change Date format to dd/mm/yyyy");
            }
        }
    }

    /**
     * Parses the event command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @return A string representing the event command.
     * @throws DukeException Throws a DukeException.
     */
    public String parseEvent(String[] subCmd) throws DukeException {
        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            String[] tempSplit = tmp.split(" /at ");
            String[] prioritySplit = tempSplit[1].split(" /p ");
            Event tmpTask = new Event(tempSplit[0], false, prioritySplit[0], PriorityLevel.getPriorityString(prioritySplit[1]));
            String EventMessage = this.taskList.addTask(tmpTask);
            this.storage.save(this.taskList);
            return EventMessage;
        }
    }

    /**
     * Parses the delete command.
     * @param subCmd An Array of Strings containing the remaining command arguments.
     * @return A string representing the delete command.
     * @throws DukeException Throws a DukeException.
     */
    public String parseDelete(String[] subCmd) throws DukeException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            String deleteMessage = this.taskList.deleteTask(Integer.parseInt(subCmd[0]));
            this.storage.save(this.taskList);
            return deleteMessage;
        }
    }

    /**
     * Parses the find command.
     * @param subCmd String representation of the command.
     * @return A string representing the find command.
     * @throws DukeException Throws DukeException.
     */
    public String parseFind(String[] subCmd) throws DukeException {
        String temp = String.join(" ", subCmd);
        if (temp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            return this.taskList.findKeyword(temp);
        }
    }
}
