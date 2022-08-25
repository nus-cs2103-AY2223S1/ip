package dobby;

import dobby.commands.*;
import dobby.tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    //method for interpreting User Input
    public static String getDesc(String rest) {
        int endIndex = rest.indexOf("/") - 1;
        return rest.substring(0, endIndex);
    }
    public static String getDate(String rest) {
        String dateFormatted = "";
        try {
            int i = rest.indexOf("/");
            if (i == -1) {
                return "noDate";
            }
            String dateString = rest.substring(i + 4);
            dateFormatted = dateFormat(dateString, "yyyy-MM-dd HHmm");
            return dateFormatted;
        } catch (DateTimeParseException e) {
            DobbyChat.wrongDateFormat();
        }
        return "wrongDateFormat";
    }
    public static String getCmd(String task) {
        return task.split(" ")[0];
    }
    public static String getRest(String task) {
        int firstSpace = task.indexOf(" ");
        String rest = task.substring(firstSpace + 1);
        return rest;
    }

    //methods for interpreting .txt file
    public static boolean getStatusTxt(String input) {
        boolean isDone = input.charAt(5) == 'X';
        return isDone;
    }
    public static String getTaskTypeTxt(String input) {
        String task = Character.toString(input.charAt(0));
        return task;
    }
    public static String getRestTxt(String input) {
        String rest = input.substring(10);
        return rest;
    }
    public static String getDescTxt(String rest) {
        int endIndex = rest.indexOf("|") - 1;
        String desc = rest.substring(0, endIndex);
        return desc;
    }
    public static String getDateTxt(String rest) {
        int startIndex = rest.indexOf("|") + 2;
        String date = rest.substring(startIndex);
        return date;
    }


    //method for interpreting time
    public static String dateFormat(String dateString, String datePattern) {
        //date format for intended input
        DateTimeFormatter form = DateTimeFormatter.ofPattern(datePattern);
        LocalDateTime date = LocalDateTime.parse(dateString, form);
        String dateFormatted = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return dateFormatted;
    }

    //method to create new Command object
    public static Command parse(String cmd) {
        switch(cmd) {
        case "bye" :
        case "quit":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "delete":
            return new DeleteCommand();
        case "todo":
        case "event":
        case "deadline":
            return new TaskCommand();
        default:
            return new ErrorCommand();
        }
    }
}
