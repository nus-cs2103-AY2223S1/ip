package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.CommandWord;
import duke.exception.DukeException;
import duke.task.TaskType;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String strippedCommand = fullCommand.stripTrailing();
        String[] parsedCommand = strippedCommand.split(" ", 2);
        try {
            switch (parsedCommand[0]) {
            case "":
                throw new DukeException("  Somehow you're even more unbearable when you're silent.");
            case "todo":
                return validateToDo(parsedCommand);
            case "deadline":
                return validateDeadline(parsedCommand);
            case "event":
                return validateEvent(parsedCommand);
            case "delete":
                return validateDelete(parsedCommand);
            case "mark":
                return validateMark(parsedCommand);
            case "unmark":
                return validateUnmark(parsedCommand);
            case "list":
                return validateList(strippedCommand);
            case "bye":
                return validateBye(strippedCommand);
            default:
                throw new DukeException(Ui.getUnknownCommandMessage(strippedCommand));
            }
        } catch (NumberFormatException e) {
            throw new DukeException("  Do you need me to teach you what a number is?\n"
                    + "  '" + parsedCommand[1] + "' is not a number.");
        }
    }

    public static LocalDateTime convertStringToDateTime(String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("  Invalid date or time: '" + dateTime + "'\n"
                    + "  Please enter a date and time with the following format: 'dd/mm/yyyy HHMM'");
        }
    }

    public static LocalDate convertStringToDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("  Invalid date: '" + date + "'\n"
                    + "  Please enter a date with the following format: 'dd/mm/yyyy'");
        }
    }
    
    public static String[] parseSavedTask(String savedTask) {
        return savedTask.split(" \\| ", 4);
    }
    
    private static Command validateToDo(String[] parsedCommand) throws DukeException {
        if (parsedCommand.length < 2) {
            throw new DukeException("  It seems you've invented a way to do nothing. Typical...\n"
                    + Ui.getCommandHelp(CommandWord.TODO));
        } else if (parsedCommand[1].contains("|")) {
            throw new DukeException("  '|' characters are not allowed in the task description.");
        } else {
            return new AddCommand(TaskType.TODO, parsedCommand[1]);
        }
    }

    private static Command validateDeadline(String[] parsedCommand) throws DukeException {
        if (parsedCommand.length < 2) {
            throw new DukeException("  It seems you've invented a way to do nothing. Typical...\n"
                    + Ui.getCommandHelp(CommandWord.DEADLINE));
        } else {
            String[] parsedTask = parsedCommand[1].split(" /by ", 2);
            if (parsedTask[0].startsWith("/by") || parsedTask[0].isBlank()) {
                throw new DukeException("  It seems you've invented a way to do nothing. Typical...\n"
                        + Ui.getCommandHelp(CommandWord.DEADLINE));
            } else if (parsedTask.length < 2) {
                throw new DukeException("  You do realise deadlines and events usually have a time or date,"
                        + " right?\n" + Ui.getCommandHelp(CommandWord.DEADLINE));
            } else if (parsedTask[0].contains("|")) {
                throw new DukeException("  '|' characters are not allowed in the task description.");
            } else {
                return new AddCommand(TaskType.DEADLINE, parsedTask[0], parsedTask[1]);
            }
        }
    }

    private static Command validateEvent(String[] parsedCommand) throws DukeException {
        if (parsedCommand.length < 2) {
            throw new DukeException("  It seems you've invented a way to do nothing. Typical...\n"
                    + Ui.getCommandHelp(CommandWord.EVENT));
        } else {
            String[] parsedTask = parsedCommand[1].split(" /at ", 2);
            if (parsedTask[0].startsWith("/at") || parsedTask[0].isBlank()) {
                throw new DukeException("  It seems you've invented a way to do nothing. Typical...\n"
                        + Ui.getCommandHelp(CommandWord.EVENT));
            } else if (parsedTask.length < 2) {
                throw new DukeException("  You do realise deadlines and events usually have a time or date,"
                        + " right?\n" + Ui.getCommandHelp(CommandWord.EVENT));
            } else if (parsedTask[0].contains("|")) {
                throw new DukeException("  '|' characters are not allowed in the task description.");
            } else {
                return new AddCommand(TaskType.EVENT, parsedTask[0], parsedTask[1]);
            }
        }
    }

    private static Command validateDelete(String[] parsedCommand) throws DukeException {
        if (parsedCommand.length < 2) { // blank task number
            throw new DukeException("  Enter a task number, nitwit.\n" + Ui.getCommandHelp(CommandWord.DELETE));
        } else {
            int num = Integer.parseInt(parsedCommand[1]);
            return new DeleteCommand(num);
        }
    }

    private static Command validateMark(String[] parsedCommand) throws DukeException {
        if (parsedCommand.length < 2) { // blank task number
            throw new DukeException("  Enter a task number, nitwit.\n" + Ui.getCommandHelp(CommandWord.MARK));
        } else {
            int num = Integer.parseInt(parsedCommand[1]);
            return new MarkCommand(num);
        }
    }

    private static Command validateUnmark(String[] parsedCommand) throws DukeException {
        if (parsedCommand.length < 2) { // blank task number
            throw new DukeException("  Enter a task number, nitwit.\n" + Ui.getCommandHelp(CommandWord.UNMARK));
        } else {
            int num = Integer.parseInt(parsedCommand[1]);
            return new UnmarkCommand(num);
        }
    }

    private static Command validateList(String strippedCommand) throws DukeException {
        if (strippedCommand.equals("list")) {
            return new ListCommand();
        } else {
            throw new DukeException(Ui.getUnknownCommandMessage(strippedCommand));
        }
    }

    private static Command validateBye(String strippedCommand) throws DukeException {
        if (strippedCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new DukeException(Ui.getUnknownCommandMessage(strippedCommand));
        }
    }

}
