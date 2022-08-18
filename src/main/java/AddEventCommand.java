import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AddEventCommand implements DukeCommand {
    public Pattern pattern = Pattern.compile("[\\S+]\\s/at\\s[\\S+]");

    public String run (List<Task> taskList, String content) throws DukeException {
        if (!pattern.matcher(content).find()) {
            return "Event must be in this format: <Description> /at <DateTime>\n";
        }
        String[] detail = content.split(" /at ", 2);
        Event event = new Event(detail[0], detail[1]);
        taskList.add(event);
        return "Added a event: " + event.toString();
    }
}
