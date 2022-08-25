package handlers;

import exceptions.DukeException;
import models.Event;
import models.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class AddEventCommand implements DukeCommand {
    public Pattern pattern = Pattern.compile("[\\S+]\\s/at\\s[\\S+]");

    public String run (TaskList taskList, String content) throws DukeException {
        if (!pattern.matcher(content).find()) {
            throw new DukeException("Event must be in this format: <Description> /at <DateTime>\n");
        }
        String[] detail = content.split(" /at ", 2);
        try{
            LocalDate date = LocalDate.parse(detail[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Event event = new Event(detail[0], date);
            taskList.AddTask(event);
            return "Added a event: " + event.toString();
        } catch (
        DateTimeParseException e) {
            throw new DukeException("Your date must be a valid date in dd/MM/yyyy format\n");
        }
    }
}
