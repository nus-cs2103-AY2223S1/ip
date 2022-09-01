package bro;

import bro.command.AddCommand;
import bro.command.Command;
import bro.command.DeleteCommand;
import bro.command.ExitCommand;
import bro.command.ListCommand;
import bro.command.ModifyCommand;
import bro.task.Deadline;
import bro.task.Event;
import bro.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public Command parse(String str) throws BroException {

        String[] in = str.split(" ");
        String input = in[0];
        this.checkEmptyInput(str);
        this.checkInput(input, in.length);
            switch (input) {
            case "list":
                return new ListCommand();
            case "mark":
                return new ModifyCommand(ModifyCommand.ModifyType.MARK, Integer.parseInt(in[1].trim()));
            case "unmark":
                return new ModifyCommand(ModifyCommand.ModifyType.UNMARK, Integer.parseInt(in[1].trim()));
            case "todo":
                return new AddCommand(new Todo(in[1]));
            case "deadline":
                return new AddCommand(new Deadline(in[1], str.split("/by")[1].trim()));
            case "event":
                return new AddCommand(new Event(in[1], str.split("/at")[1].trim()));
            case "delete":
                return new DeleteCommand(Integer.parseInt(in[1].trim()));
            case "bye":
                return new ExitCommand();
            default:
                throw new BroException("Idk what it means!");
            }
        }

    public void checkEmptyInput(String input) throws BroException {
        String[] list = new String[]{"todo", "deadline", "event", "mark", "unmark"};
        List<String> checkList = new ArrayList<>(Arrays.asList(list));
        if (checkList.contains(input)) {
            throw new BroException("The description cannot be empty.");
        }
    }

    public void checkInput(String str, int n) throws BroException {
        switch (str){
        case "todo":
            if(n < 2) { throw new BroException("Please give the description!");}
            break;
        case "deadline":
        case "event":
            if(n < 4) { throw new BroException("Please give the description and time!"); }
            break;
        }
    }

    public static LocalDateTime deadlineParser(String by) throws BroException {
        try {
            return LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));
        } catch (DateTimeParseException e){
            throw new BroException("Please enter the date in the format dd/MM/yyyy kkmm");
        }
    }

    public static LocalDateTime eventParser(String at) throws BroException {
        try {
            return LocalDateTime.parse(at.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));
        }catch (DateTimeParseException e){
            throw new BroException("Please enter the date in the format dd/MM/yyyy kkmm");
        }
    }
}
