package duke.main;

import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses user input into commands to be executed.
     *
     * @param input user input
     * @return Command to be executed.
     * @throws DukeException  If illegal input is entered.
     */
    static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            int taskNum = Integer.parseInt(input.replace("mark ", ""));
            validateMark(taskNum);
            return new DoneCommand(taskNum);
        } else if (input.startsWith("unmark ")) {
            int taskNum = Integer.parseInt(input.replace("unmark ", ""));
            validateMark(taskNum);
            return new UndoneCommand(taskNum);
        } else if (input.startsWith("deadline ")) {
            String[] deadline = input.replace("deadline ", "").split(" /by ");
            if (isDate(deadline[1])) {
                LocalDateTime byDate = parseDate(deadline[1]);
                return new AddDeadlineCommand(deadline[0], byDate);
            } else {
                return new AddDeadlineCommand(deadline[0], deadline[1]);
            }
        } else if (input.startsWith("todo ")) {
            String todo = input.replace("todo ", "");
            validateTodo(todo);
            return new AddTodoCommand(todo);
        } else if (input.startsWith("event ")) {
            String[] event = input.replace("event ", "").split(" /at ");
            return new AddEventCommand(event[0], event[1]);
        } else if (input.startsWith("delete ")) {
            int taskNum = Integer.parseInt(input.replace("delete ", ""));
            validateDelete(taskNum);
            return new DeleteCommand(taskNum);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Identifies if the deadline entered is a date.
     *
     * @param input deadline entered by user
     * @return true if the deadline entered is a date.
     */
    private static boolean isDate(String input) {
        // Assume date is in the format 2/12/2019 1800
        String[] splitInput = input.split("/");
        if (splitInput.length != 3)  {
            return false;
        }

        String[] yearAndTime = splitInput[2].split(" ");
        if (yearAndTime.length != 2) {
            return false;
        }

        return true;
    }

    /**
     * Parses date entered from string into LocalDateTime
     *
     * @param date date entered
     */
    private static LocalDateTime parseDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(date, dateFormatter);
    }

    /**
     * Checks whether the description is empty.
     *
     * @param todo description of todo
     * @throws DukeException  If description is empty.
     */
    public static void validateTodo(String todo) throws DukeException {
        if(todo.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Checks whether the index of task to be marked as done is in legal range.
     *
     * @param taskNum index of task to be marked as done
     * @throws DukeException  If index is < 1.
     */
    public static void validateMark(int taskNum) throws DukeException {
        if(taskNum < 1) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }
    }

    /**
     * Checks whether the index of task to be deleted is in legal range.
     *
     * @param taskNum index of task to be deleted
     * @throws DukeException  If index is < 1.
     */
    public static void validateDelete(int taskNum) throws DukeException {
        if(taskNum < 1) {
            throw new DukeException("OOPS!!! The index of the task to delete is not in the list.");
        }
    }
}
