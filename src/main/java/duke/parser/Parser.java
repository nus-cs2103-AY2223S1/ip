package duke.parser;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the logic of parsing the user input.
 */
public class Parser {
    protected String input;
    protected String[] inputArr;

    public Parser(String input) {
        this.input = input;
        this.inputArr = input.split(" ");
    }

    /**
     * Returns the first word that user inputted.
     *
     * @return First word inputted
     */
    public String getFirstText() {
        return inputArr[0];
    }

    public int getTaskNumber() {
        return Integer.parseInt(inputArr[1]);
    }

    /**
     * Return the description of the to-do task.
     *
     * @return Description of to-do task
     * @throws DukeException If input description is empty
     */
    public String getTodoDescription() throws DukeException {
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex == -1) {
            throw new DukeException("Description of a todo cannot be empty dummy!");
        }
        String desc = input.substring(firstSpaceIndex + 1);
        if (desc.trim().equals("")) {
            throw new DukeException("Description of a todo cannot be empty dummy!");
        }
        return desc;
    }

    public String getDeadlineDescription() throws DukeException {
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex == -1) {
            throw new DukeException("Description of a deadline cannot be empty dummy!");
        }
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("A deadline must have a by clause dummy!");
        }
        String desc = input.substring(firstSpaceIndex + 1, byIndex);
        if (desc.trim().equals("")) {
            throw new DukeException("Description of a deadline cannot be empty dummy!");
        }
        return desc;
    }

    /**
     * Return date of the deadline.
     *
     * @return Date of the deadline in LocalDate type
     * @throws DukeException If there is not /by clause
     */
    public LocalDate getDeadlineDate() throws DukeException {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("A deadline must have a by clause dummy!");
        }
        String by = input.substring(byIndex + 4);
        String[] byArr = by.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate byDate = LocalDate.parse(byArr[0], formatter);
        return byDate;
    }

    public String getEventDescription() throws DukeException {
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex == -1) {
            throw new DukeException("Description of an event cannot be empty dummy!");
        }
        int atIndex = input.indexOf("/at");
        if (atIndex == -1) {
            throw new DukeException("An event must have a at clause dummy!");
        }
        String desc = input.substring(firstSpaceIndex + 1, atIndex);
        if (desc.trim().equals("")) {
            throw new DukeException("Description of an event cannot be empty dummy!");
        }
        return desc;
    }

    public LocalDate getEventDate() throws DukeException {
        int atIndex = input.indexOf("/at");
        if (atIndex == -1) {
            throw new DukeException("An event must have a at clause dummy!");
        }
        String at = input.substring(atIndex + 4);
        String[] atArr = at.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate atDate = LocalDate.parse(atArr[0], formatter);
        return atDate;
    }

    /**
     * Return keyword after first word find was inputted.
     *
     * @return A single keyword
     * @throws DukeException If there is no keyword inputted after "find"
     */
    public String getKeyword() throws DukeException {
        if (inputArr.length == 1) {
            throw new DukeException("You need to type in a keyword to find!!");
        }
        String keyword = inputArr[1];
        if (keyword.trim().equals("")) {
            throw new DukeException("You need to type in a keyword to find!!");
        }
        return keyword;
    }


}
