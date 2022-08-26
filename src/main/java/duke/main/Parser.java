package duke.main;

import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
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
        } else if (input.startsWith("find ")) {
            String toFind = input.replace("find ", "");
            validateFind(toFind);
            return new FindCommand(toFind);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

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

    private static LocalDateTime parseDate(String date) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(date, dateFormatter);
    }

    public static void validateTodo(String todo) throws DukeException {
        if(todo.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void validateFind(String toFind) throws DukeException {
        if(toFind.isEmpty()) {
            throw new DukeException("OOPS!!! The task to find cannot be empty.");
        }
    }

    public static void validateMark(int taskNum) throws DukeException {
        if(taskNum < 1) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }
    }

    public static void validateDelete(int taskNum) throws DukeException {
        if(taskNum < 1) {
            throw new DukeException("OOPS!!! The index of the task to delete is not in the list.");
        }
    }
}
