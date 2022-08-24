package duke.inputparser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.DukeException;
import duke.ui.BotUI;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.Command;
import duke.command.MarkCommand;
import duke.command.FindCommand;
import duke.common.InputChecker;


public class Parser {

    private static final BotUI UI = new BotUI();

    private static String[] splitInput(String input, String regex) {
        return input.split(regex, 2);
    }

    public static Command parse(String rawInput) throws DukeException {
        try{
            String[] commandAndDetail = rawInput.split(" ", 2);
            String command = commandAndDetail[0];
            if (commandAndDetail.length < 2) {
                if (command.equals("list")) {
                    return new ListCommand(command);
                } else if (command.equals("bye")) {
                    return new ExitCommand(command);
                }
            }
            String detail = commandAndDetail[1];
            if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                InputChecker.checkInput(rawInput);
                return new AddCommand(command, detail);
            } else if (command.equals("mark") || command.equals("unmark")) {
                return new MarkCommand(command, detail);
            } else if (command.equals("delete")) {
                return new DeleteCommand(command, detail);
            } else if (command.equals("find")){
                return new FindCommand(command, detail);
            } else {
                throw new DukeException(UI.invalidFormat());
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(UI.invalidFormat());
        }
    }

    public static LocalDateTime extractDateTime(String rawInput, String timeIdentifier) throws DukeException {
        try {
            String filterDate = splitInput(rawInput, timeIdentifier)[1];
            String[] dateAndTime = filterDate.split(" ");
            int time = Integer.parseInt(dateAndTime[1]);
            int hours = time / 100;
            int minutes = time % 100;
            String[] splitDate = dateAndTime[0].split("-");
            ArrayList<Integer> dateInfo = new ArrayList<Integer>();
            for (String s : splitDate) {
                dateInfo.add(Integer.parseInt(s));
            }
            return LocalDateTime.of(dateInfo.get(0), dateInfo.get(1), dateInfo.get(2),
                    hours, minutes);
        } catch(DateTimeException ex) {
            throw new DukeException(UI.invalidDateFormat());
        }

    }

    public static String extractDetail(String filteredInput, String timeIdentifier) {
        return splitInput(filteredInput, timeIdentifier)[0];
    }

    public static LocalDateTime convertTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return LocalDateTime.parse(timeString, formatter);
    }

}
