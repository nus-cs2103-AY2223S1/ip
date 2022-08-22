package duke.duke.ui;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private enum Inputs {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        ELSE
    }

    public static Command parse(String command) throws duke.ui.DukeException, DateTimeParseException {
        switch (getInput(command)) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            int markNum = Integer.parseInt(command.replace("mark ", ""));
            return new MarkCommand(markNum - 1);
        case UNMARK:
            int unMarkNum = Integer.parseInt(command.replace("unmark ", ""));
            return new UnMarkCommand(unMarkNum - 1);
        case TODO:
            String tDes = command.replace("todo", "");
            return new AddCommand(new Todo(tDes));
        case EVENT:
            String[] eDes = command.replace("event", "").split(" /at ");
            return new AddCommand(new Event(eDes[0], LocalDate.parse(eDes[1])));
        case DEADLINE:
            String[] dDes = command.replace("deadline", "").split(" /by ");
            return new AddCommand(new Deadline(dDes[0], LocalDate.parse(dDes[1])));
        case DELETE:
            int delNum = Integer.parseInt(command.replace("delete ", ""));
            return new DeleteCommand(delNum);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Inputs getInput(String s) {
        if (s.equals("bye")) {
            return Inputs.BYE;
        } else if (s.equals("list")) {
            return Inputs.LIST;
        } else if (s.startsWith("mark")) {
            return Inputs.MARK;
        } else if (s.startsWith("unmark")) {
            return Inputs.UNMARK;
        } else if (s.startsWith("todo")) {
            return Inputs.TODO;
        } else if (s.startsWith("deadline")) {
            return Inputs.DEADLINE;
        } else if (s.startsWith("event")) {
            return Inputs.EVENT;
        } else if (s.startsWith("delete")) {
            return Inputs.DELETE;
        } else {
            return Inputs.ELSE;
        }
    }
}
