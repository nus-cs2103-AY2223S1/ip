package duke.parser;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    protected String input;
    protected String[] inputArr ;

    public Parser(String input) {
        this.input = input;
        this.inputArr = input.split(" ");
    }

    public String getFirstText() {
        return inputArr[0];
    }

    public int getTaskNumber() {
        return Integer.parseInt(inputArr[1]);
    }

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


}
