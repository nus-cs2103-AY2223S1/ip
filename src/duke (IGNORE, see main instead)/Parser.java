package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static String getCommandType(String input) {
        if (input.equals("list")) {
            return "LIST";
        } else if (input.startsWith("mark ")) {
            return "MARK";
        } else if (input.startsWith("unmark ")) {
            return "UNMARK";
        } else if (input.startsWith("todo ")) {
            return "TODO";
        } else if (input.startsWith("deadline ")) {
            return "DEADLINE";
        } else if (input.startsWith("event ")) {
            return "EVENT";
        } else if (input.startsWith("delete ")) {
            return "DELETE";
        } else if (input.equals("bye")) {
            return "BYE";
        } else { // to handle unknown inputs, e.g. 'blah', 'todo'
            throw new IllegalArgumentException();
        }
    }

    public static LocalDateTime formatEventTime(String input) {
        try {
            String time = input.substring(input.indexOf("/") + 4); // Format: dd/MM/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException dtpe) {
            System.out.println("Please give me a correct time/date! :(");
            return null;
        }
    }
}