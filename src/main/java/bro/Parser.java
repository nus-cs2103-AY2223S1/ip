package bro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bro.command.AddCommand;
import bro.command.Command;
import bro.command.DeleteCommand;
import bro.command.ExitCommand;
import bro.command.FindCommand;
import bro.command.HelpCommand;
import bro.command.ListCommand;
import bro.command.ModifyCommand;
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
        switch (input) {
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "mark":
            assert in.length == 2 : "in doesn't have length of 2";
            return new ModifyCommand(ModifyCommand.ModifyType.MARK, intParser(in[1]));
        case "unmark":
            assert in.length == 2 : "in doesn't have length of 2";
            return new ModifyCommand(ModifyCommand.ModifyType.UNMARK, intParser(in[1]));
        case "todo":
            assert in.length == 2 : "in doesn't have length of 2";
            return new AddCommand(new Todo(checkValid(in, -1)));
        case "deadline":
            assert in[1].split("/by")[1].trim() != "" : "Time is empty";
            System.out.println(in[1].split("/by").length);
            return new AddCommand(new Deadline(checkValid(in[1].split("/by"), 0),
              checkValid(in[1].split("/by"), 1)));
        case "event":
            assert in[1].split("/at")[1].trim() != "" : "Time is empty";
            return new AddCommand(new Event(checkValid(in[1].split("/at"), 0),
              checkValid(in[1].split("/at"), 1)));
        case "delete":
            assert in.length == 2 : "in doesn't have length of 2";
            return new DeleteCommand(intParser(in[1]));
        case "bye":
        case "BYE":
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
        String[] list = new String[]{"todo", "deadline", "event", "mark", "unmark", "find", "delete"};
        List<String> checkList = new ArrayList<>(Arrays.asList(list));
        if (checkList.contains(input)) {
            throw new BroException("The description cannot be empty.");
        }
    }
    /**
     * Checks whether the string can be parsed to int.
     * @param integer The String number to be converted to int.
     * @return The int form of String integer.
     * @throws BroException If the string cannot be parsed to int.
     */
    public int intParser(String integer) throws BroException {
        try {
            return Integer.parseInt(integer.trim());
        } catch (NumberFormatException e) {
            throw new BroException("Please give a number!!");
        }
    }
    /**
     * Checks whether the deadline or event is in the correct format.
     * @param arr Array after splitting the input.
     * @param n The index of the array word to be returned.
     * @return The word at the specified n.
     * @throws BroException If the deadline or event is not in the correct format.
     */
    public String checkValid(String[] arr, int n) throws BroException {
        if (n == -1) {
            if (arr.length == 1 || arr[1].trim().equals("")) {
                throw new BroException("Please type the command in correct format!!");
            } else {
                return arr[1].trim();
            }
        } else if (arr.length <= 1 || (arr[0].equals(""))) {
            throw new BroException("Please type the command in correct format!!");
        } else {
            return arr[n].trim();
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
            throw new BroException("Please enter the date in the format dd/MM/yyyy hhmm");
        }
    }
}
