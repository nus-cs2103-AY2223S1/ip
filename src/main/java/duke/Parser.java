package duke;

import duke.command.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String command, TaskList taskList) throws DukeException,
            DateTimeParseException, NumberFormatException{
        if (command.toLowerCase().equals("bye")) {
            return new ByeCommand();
        }
        if (command.toLowerCase().equals("list")) {
            return new ListCommand();
        }
        if (command.toLowerCase().startsWith("mark")) {
            int position = parseMarkedTask(command, taskList);
            return new MarkCommand(position);
        }
        if (command.toLowerCase().startsWith("unmark")) {
            int position = parseUnmarkedTask(command, taskList);
            return new UnmarkCommand(position);
        }
        if (command.toLowerCase().startsWith("todo")) {
            String description = parseToDo(command);
            return new ToDoCommand(description);
        }
        if (command.toLowerCase().startsWith("deadline")) {
           String[] taskWithDeadline = parseDeadline(command);
           LocalDate date = parseDate(taskWithDeadline[1]);
           return new DeadlineCommand(taskWithDeadline[0], date);
        }
        if (command.toLowerCase().startsWith("event")) {
            String[] taskWithPeriod = parseEvent(command);
            LocalDate date = parseDate(taskWithPeriod[1]);
            return new EventCommand(taskWithPeriod[0], date);
        }
        if (command.toLowerCase().startsWith("delete")) {
            int position = parseDelete(command, taskList);
            return new DeleteCommand(position);
        }
        if (command.toLowerCase().startsWith("find")) {
            String keyword = parseFind(command);
            return new FindCommand(keyword);
        }
        return new WrongCommand();
    }

    private static Integer parseMarkedTask(String command, TaskList taskList) throws DukeException {
        String[] markedTask = command.split(" ", 2);
        if (markedTask.length < 2) {
            throw new DukeException("Please input a number after mark");
        }
        int number = Integer.parseInt(markedTask[1]);
        if (number > taskList.getSize() || number <= 0) {
            throw new DukeException("Sorry! Please choose a valid item number in the list");
        }
        return number;
    }

    private static Integer parseUnmarkedTask(String command, TaskList taskList) throws DukeException {
        String[] unmarkedTask = command.split(" ", 2);
        if (unmarkedTask.length < 2) {
            throw new DukeException("Please input a number after unmark");
        }
        int number = Integer.parseInt(unmarkedTask[1]);
        if (number > taskList.getSize() || number <= 0) {
            throw new DukeException("Sorry! Please choose a valid item number in the list");
        }
        return number;
    }

    private static String parseToDo(String command) throws DukeException{
        String[] taskWithDescription = command.split(" ", 2);
        if (taskWithDescription.length < 2) {
            throw new DukeException("Sorry! Please include a description after todo");
        }
        return taskWithDescription[1];
    }

    private static String[] parseDeadline(String command) throws DukeException {
        String[] taskWithDescription = command.split(" ",2);
        if (taskWithDescription.length < 2) {
            throw new DukeException("Sorry! Please include a " +
                    "valid description after deadline");
        }
        String[] taskWithDeadline = taskWithDescription[1]
                .split(" /by ",2);
        if (taskWithDeadline.length < 2) {
            throw new DukeException("Sorry! Please include a valid " +
                    "deadline after the description");
        }

        return taskWithDeadline;
    }

    private static LocalDate parseDate(String date) throws DateTimeParseException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date,dtf);
    }

    private static String[] parseEvent(String command) throws DukeException {
        String[] taskWithDescription = command.split(" ",2);
        if (taskWithDescription.length < 2) {
            throw new DukeException("Sorry! Please include a " +
                    "valid description after event");
        }
        String[] taskWithPeriod = taskWithDescription[1]
                .split(" /at ",2);
        if (taskWithPeriod.length < 2) {
            throw new DukeException("Sorry! Please include a valid " +
                    "period after the description");
        }
        return taskWithPeriod;
    }

    private static Integer parseDelete(String command, TaskList taskList) throws DukeException, NumberFormatException{
        String[] taskDeleted = command.split(" ", 2);
        if (taskDeleted.length < 2) {
            throw new DukeException("Sorry! Please include a number after delete");
        }
        int number = Integer.parseInt(taskDeleted[1]);
        if (number > taskList.getSize() || number <= 0) {
            throw new DukeException("Sorry! Please select a valid item number in the list");
        }
        return number;
    }

    private static String parseFind(String command) throws DukeException {
        String[] commandWithKeyword = command.split(" ",2);
        if (commandWithKeyword.length < 2) {
            throw new DukeException("Sorry! Please include a keyword after find");
        }
        return commandWithKeyword[1];
    }

}
