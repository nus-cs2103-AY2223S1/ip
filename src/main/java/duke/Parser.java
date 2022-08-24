package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;

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
                } catch (StringIndexOutOfBoundsException e){
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
                    LocalTime time = LocalTime.of(Integer.parseInt(arrOfStr[1].substring(0,2)),
                            Integer.parseInt(arrOfStr[1].substring(2)));
                    Deadline deadline = new Deadline(name, date, time);
                    AddCommand addDeadlineCommand = new AddCommand(deadline);
                    return addDeadlineCommand;
                }
                catch (Exception e){
                    System.out.println("Invalid Input for date and time");
                }

            case "event":
                int endIndex = command.indexOf('/');
                String eventName = command.substring(6, endIndex - 1 );
                String time = command.substring(endIndex + 4);
                Event event = new Event(eventName,time);
                AddCommand addEventNameCommand = new AddCommand(event);
                return addEventNameCommand;

            default:
                DefaultCommand defaultCommand = new DefaultCommand();
                return defaultCommand;
        }

    }


}
