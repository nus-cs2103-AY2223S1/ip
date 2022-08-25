package handlers;

import exceptions.DukeException;
import models.Deadline;
import models.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class AddDeadlineCommand implements DukeCommand {
    public Pattern pattern = Pattern.compile("[\\S+]\\s/by\\s[\\S+]");

    public String run (TaskList taskList, String content) throws DukeException {

        if (!pattern.matcher(content).find()) {
            throw new DukeException("Deadline must be in this format: <Description> /by <DateTime>\n");
        }

        String[] detail = content.split(" /by ", 2);
        try {
            LocalDate date = LocalDate.parse(detail[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Deadline ddl = new Deadline(detail[0], date);
            taskList.AddTask(ddl);
            return "Added a deadline: " + ddl.toString();
        } catch (DateTimeParseException e) {
            throw new DukeException("Your date must be a valid date in dd/MM/yyyy format\n");
        }
    }
}
