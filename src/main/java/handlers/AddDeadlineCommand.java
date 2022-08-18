package handlers;

import exceptions.DukeException;
import models.Deadline;
import models.Task;

import java.util.List;
import java.util.regex.Pattern;

public class AddDeadlineCommand implements DukeCommand {
    public Pattern pattern = Pattern.compile("[\\S+]\\s/by\\s[\\S+]");

    public String run (List<Task> taskList, String content) throws DukeException {

        if (!pattern.matcher(content).find()) {
            throw new DukeException("Deadline must be in this format: <Description> /by <DateTime>\n");
        }

        String[] detail = content.split(" /by ", 2);
        Deadline ddl = new Deadline(detail[0], detail[1]);
        taskList.add(ddl);
        return "Added a deadline: " + ddl.toString();
    }
}
