package bro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bro.command.*;
import bro.task.Deadline;
import bro.task.Event;
import bro.task.Todo;

/**
 * Parser Class.
 */
public class Parser {

    /**
     * Returns new Command with what has to be done.
     * @param str Input of the user.
     * @return The Command.
     * @throws BroException If the input has no meaning.
     */
    public Command parse(String str) throws BroException {

        String[] in = str.split(" ", 2);
        String input = in[0];
        this.checkEmptyInput(str);
        this.checkInput(input, str.split(" ").length);
        switch (input) {
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "mark":
            assert in.length == 2 : "in doesn't have length of 2";
            return new ModifyCommand(ModifyCommand.ModifyType.MARK, Integer.parseInt(in[1].trim()));
        case "unmark":
            assert in.length == 2 : "in doesn't have length of 2";
            return new ModifyCommand(ModifyCommand.ModifyType.UNMARK, Integer.parseInt(in[1].trim()));
        case "todo":
            assert in.length == 2 : "in doesn't have length of 2";
            return new AddCommand(new Todo(str.split(" ", 2)[1]));
        case "deadline":
            assert in[1].split("/by")[1].trim() != "" : "Time is empty";
            return new AddCommand(new Deadline(in[1].split("/by")[0].trim(),
              in[1].split("/by")[1].trim()));
        case "event":
            assert in[1].split("/at")[1].trim() != "" : "Time is empty";
            return new AddCommand(new Event(in[1].split("/at")[0].trim(),
              in[1].split("/at")[1].trim()));
        case "delete":
            assert in.length == 2 : "in doesn't have length of 2";
            return new DeleteCommand(Integer.parseInt(in[1].trim()));
        case "bye":
            return new ExitCommand();
        case "find":
            assert in.length == 2 : "in doesn't have length of 2";
            return new FindCommand(in[1]);
        default:
            throw new BroException("Idk what it means!");
        }
    }

    /**
     * Checks whether the description has been given for the input tasks.
     * @param input The input by the user.
     * @throws BroException If the description has not been provided.
     */
    public void checkEmptyInput(String input) throws BroException {
        String[] list = new String[]{"todo", "deadline", "event", "mark", "unmark", "find"};
        List<String> checkList = new ArrayList<>(Arrays.asList(list));
        if (checkList.contains(input)) {
            throw new BroException("The description cannot be empty.");
        }
    }

    /**
     * Checks whether the time and details have been given for the task provided by the user.
     * @param str Input by the user.
     * @param n Length of the split array of the input by the user.
     * @throws BroException If the details are invalid.
     */
    public void checkInput(String str, int n) throws BroException {
        switch (str) {
        case "todo":
            if (n < 2) {
                throw new BroException("Please give the description!");
            }
            break;
        case "deadline":
        case "event":
            if (n < 4) {
                throw new BroException("Please give the description and time!");
            }
            break;
        default:
            break;
        }
    }

    /**
     * Converts the string date into LocalDateTime.
     * @param time The date and time provided for the deadline or event task.
     * @return The LocalDateTime format of the date and time.
     * @throws BroException If the by is of invalid format.
     */
    public static LocalDateTime timeParser(String time) throws BroException {
        try {
            return LocalDateTime.parse(time.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));
        } catch (DateTimeParseException e) {
            throw new BroException("Please enter the date in the format dd/MM/yyyy kkmm");
        }
    }
}
