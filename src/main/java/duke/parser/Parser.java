package duke.parser;

import duke.DukeException;
import duke.Message;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.Arrays;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] commandList = fullCommand.strip().split(" ");
        String command = commandList[0].toLowerCase();
        if (command.equals("bye") && commandList.length == 1) {
            return new ByeCommand();
        } else if (command.equals("list") && commandList.length == 1) {
            return new ListCommand();
        } else if (command.equals("mark")) {
            return markTask(fullCommand);
        } else if (command.equals("unmark")) {
            return unmarkTask(fullCommand);
        } else if (command.equals("deadline")) {
            return addDeadline(fullCommand);
        } else if (command.equals("event")) {
            return addEvent(fullCommand);
        } else if (command.equals("todo")) {
            return addToDo(fullCommand);
        } else if (command.equals("delete")) {
            return deleteTask(fullCommand);
        } else if (command.equals("find")) {
            return findTask(fullCommand);
        }
        throw new DukeException(Message.INVALID_USER_INPUT);
    }


    private static AddCommand addToDo(String input) throws DukeException {
        String description = input.substring("todo".length()).strip();
        if (!description.equals("")) {
            ToDo newToDo = new ToDo(description);
            return new AddCommand(newToDo);
        } else {
            throw new DukeException(Message.INVALID_TODO_INPUT);
        }
    }

    private static AddCommand addDeadline(String input) throws DukeException {
        String[] stringArray = input.substring("deadline".length()).strip().split("/by");
        try {
            String description = stringArray[0].strip();
            String by = stringArray[1].strip();
            LocalDate deadlineDate = LocalDate.parse(by);
            if (deadlineDate.isBefore(LocalDate.now())) {
                throw new DukeException(Message.INVALID_DATE_INPUT);
            }
            if (description.equals("")) {
                throw new DukeException(Message.INVALID_DEADLINE_INPUT);
            }
            Deadline newDeadline = new Deadline(description, deadlineDate);
            return new AddCommand(newDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Message.INVALID_DEADLINE_INPUT);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Message.INVALID_DATE_FORMAT);
        }
    }

    private static AddCommand addEvent(String input) throws DukeException {
        try {
            String[] stringArray = input.substring("event".length()).strip().split("/at");
            String description = stringArray[0].strip();
            String at = stringArray[1].strip();
            if (description.equals("") || at.equals("")) {
                throw new DukeException(Message.INVALID_EVENT_INPUT);
            }
            Event newEvent = new Event(description, at);
            return new AddCommand(newEvent);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Message.INVALID_EVENT_INPUT);
        }
    }

    private static MarkCommand markTask(String input) throws DukeException {
        String[] commandList = input.strip().split(" ");
        try {
            int taskIndexNum = Integer.parseInt(commandList[1]);
            return new MarkCommand(taskIndexNum);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(Message.INVALID_MARK_TASK_FORMAT);
        }
    }

    private static UnmarkCommand unmarkTask(String input) throws DukeException {
        String[] commandList = input.strip().split(" ");
        try {
            int taskIndexNum = Integer.parseInt(commandList[1]);
            return new UnmarkCommand(taskIndexNum);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(Message.INVALID_UNMARK_TASK_FORMAT);
        }
    }

    private static DeleteCommand deleteTask(String command) throws DukeException {
        String[] commandList = command.strip().split(" ");
        try {
            int taskIndexNum = Integer.parseInt(commandList[1]);
            return new DeleteCommand(taskIndexNum);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(Message.INVALID_DELETE_TASK_FORMAT);
        }
    }

    private static FindCommand findTask(String command) throws DukeException {
        String[] commandList = command.strip().split(" ", 2);
        try {
            String keyword = commandList[1].strip();
            if (keyword.equals("")) {
                throw new DukeException(Message.INVALID_FIND_TASK_FORMAT);
            }
            assert !keyword.equals("") : "keyword cannot be empty";
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException e) {
            assert commandList.length < 2 : "Command list should contain at most 1 element";
            throw new DukeException(Message.INVALID_FIND_TASK_FORMAT);
        }
    }

}
