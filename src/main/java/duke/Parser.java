package duke;

import exceptions.EmptyNameException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String getType(String command) {
        return command.split(" ", 2)[0].trim();

    }
    public static String getTodoName(String command) throws EmptyNameException {
        return command.split(" ", 2)[1].trim();
    }

    public static String getDeadlineName(String command) {
        return command.split("/by", 2)[0].trim().split(" ", 2)[1].trim();
    }

    public static String getDeadlineDate(String command) {
        return command.split("/by", 2)[1].trim();
    }

    public static String getEventName(String command) {
        return command.split("/at", 2)[0].trim().split(" ", 2)[1];
    }

    public static String getEventDate(String command) {
        return command.split("/at", 2)[1].trim();
    }

    public static LocalDate convertStringToDate(String stringDate){
        return LocalDate.parse(stringDate);
    }

    public static String convertDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
