package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DefaultCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkedCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Represents class that allocate the relevant commands based on the input string
 *
 * @author benjytan45678
 * @version 0.1
 */
public class Parser {
    private Command command;

    /**
     * Returns a command based on the specified user input.
     *
     * @param command Represents the user input.
     * @return A command based on the user input.
     * @throws DukeException If user input is an invalid string command.
     */
    public static Command parse(String command) throws DukeException {
        String[] strArr = command.split(" ");
        String firstWord = strArr[0];
        switch (firstWord) {
        case "bye":
            ByeCommand byecommand = new ByeCommand();
            return byecommand;

        case "mark":
            MarkCommand markcommand = new MarkCommand(Integer.parseInt(strArr[1]));
            return markcommand;

        case "unmark":
            UnMarkedCommand unmarkedcommand = new UnMarkedCommand(Integer.parseInt(strArr[1]));
            return unmarkedcommand;

        case "list":
            ListCommand listcommand = new ListCommand();
            return listcommand;

        case "delete":
            DeleteCommand deleteCommand = new DeleteCommand(Integer.parseInt(strArr[1]));
            return deleteCommand;

        case "todo":
            try {
                ToDo toDo = new ToDo(command.substring(5));
                AddCommand addToDoCommand = new AddCommand(toDo);
                return addToDoCommand;
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
            }

        case "deadline":
            int end = command.indexOf('/');
            String name = command.substring(9, end - 1);
            try {
                String dateTime = command.substring(end + 4);
                // an array containing a date and time respectively
                String[] arrOfStr = dateTime.split(" ");
                LocalDate date = LocalDate.parse(arrOfStr[0]);
                LocalTime time = LocalTime.of(Integer.parseInt(arrOfStr[1].substring(0, 2)),
                        Integer.parseInt(arrOfStr[1].substring(2)));
                Deadline deadline = new Deadline(name, date, time);
                AddCommand addDeadlineCommand = new AddCommand(deadline);
                return addDeadlineCommand;
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Input for date and time in deadline task");
            }

        case "event":
            int endIndex = command.indexOf('/');
            String eventName = command.substring(6, endIndex - 1);
            try {
                String eventDateTime = command.substring(endIndex + 4);
                String[] arrOfStr = eventDateTime.split(" ");
                LocalDate eventDate = LocalDate.parse(arrOfStr[0]);
                LocalTime eventTime = LocalTime.of(Integer.parseInt(arrOfStr[1].substring(0, 2)),
                        Integer.parseInt(arrOfStr[1].substring(2)));
                Event event = new Event(eventName, eventDate, eventTime);
                AddCommand addEventNameCommand = new AddCommand(event);
                return addEventNameCommand;
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Input for date and time in event task");
            }

        case "find":
            String keyword = strArr[1];
            FindCommand findCommand = new FindCommand(keyword);
            return findCommand;

        default:
            DefaultCommand defaultCommand = new DefaultCommand();
            return defaultCommand;

        }

    }


}
