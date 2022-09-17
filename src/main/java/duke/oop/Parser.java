package duke.oop;

import duke.DukeException;
import duke.command.*;

public class Parser {
    public static Command parse(String input) throws DukeException {
        assert (input != null) : "the input is null";
        String[] inputArray = input.split(" ", 2);
        String inputCommand = inputArray[0];
        switch (inputCommand) {
        case "bye":
            return new ExitCommand();
        case "find":
            String toFind = input.replace("find", "").trim();
            validateFind(toFind);
            String textToFind = input.split(" ")[1];
            return new FindCommand(textToFind);
        case "list":
            return new ListCommand();
        case "mark":
            int taskNum = Integer.parseInt(input.replace("mark", "").trim());
            validateMark(taskNum);
            int numToMark = Integer.parseInt(input.split(" ")[1]);
            return new MarkCommand(numToMark);
        case "unmark":
            int taskNum3 = Integer.parseInt(input.replace("unmark", "").trim());
            validateMark(taskNum3);
            int numToUnmark = Integer.parseInt(input.split(" ")[1]);
            return new UnMarkCommand(numToUnmark);
        case "delete":
            int taskNum2 = Integer.parseInt(input.replace("delete", "").trim());
            validateDelete(taskNum2);
            int numToDelete = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(numToDelete);
        case "todo":
            String todo = input.replace("todo", "").trim();
            validateTodo(todo);
            String todoName = inputArray[1].trim();
            return new AddTodoCommand(todoName);
        case "event":
            String event = input.replace("event", "").trim();
            validateDeadline(event);
            String[] eventArr = input.replace("event", "").split(" /at ");
            String eventName = eventArr[0];
            String eventTime = eventArr[1];
            return new AddEventCommand(eventName, eventTime);
        case "deadline":
            String deadline = input.replace("deadline", "").trim();
            validateDeadline(deadline);
            String[] deadlineArr = input.replace("deadline", "").split(" /by ");
            String deadlineName = deadlineArr[0];
            String deadlineTime = deadlineArr[1];
            return new AddDeadlineCommand(deadlineName, deadlineTime);
        case "reminder":
            return new ReminderCommand();
        default:
            throw new DukeException("Shindo, I am confused...");
        }
    }


    /**
     * Checks whether the description is empty.
     *
     * @param todo description of todo
     * @throws DukeException If description is empty.
     */
    public static void validateTodo(String todo) throws DukeException {
        if (todo.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Checks whether the description is empty.
     *
     * @param event description of event
     * @throws DukeException If description is empty.
     */
    public static void validateEvent(String event) throws DukeException {
        if (event.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Checks whether the deadline is empty.
     *
     * @param deadline description of deadline
     * @throws DukeException If description is empty.
     */
    public static void validateDeadline(String deadline) throws DukeException {
        if (deadline.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
    }


    public static void validateFind(String toFind) throws DukeException {
        if (toFind.isEmpty()) {
            throw new DukeException("OOPS!!! The task to find cannot be empty.");
        }
    }

    /**
     * Checks whether the index of task to be marked as done is in legal range.
     *
     * @param taskNum index of task to be marked as done
     * @throws DukeException If index is < 1.
     */
    public static void validateMark(int taskNum) throws DukeException {
        if (taskNum < 1) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }
    }

    /**
     * Checks whether the index of task to be deleted is in legal range.
     *
     * @param taskNum index of task to be deleted
     * @throws DukeException If index is < 1.
     */
    public static void validateDelete(int taskNum) throws DukeException {
        if (taskNum < 1) {
            throw new DukeException("OOPS!!! The index of the task to delete is not in the list.");
        }
    }
}
