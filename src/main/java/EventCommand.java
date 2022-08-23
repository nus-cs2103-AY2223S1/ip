import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command{
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String[] splitDetailEvent = this.input.split("/at", 2);
        if (splitDetailEvent.length == 1) {
            throw new DukeException("Description of event must be followed by /at then followed by time of event.");
        } else {
            String eventAction = splitDetailEvent[0].trim();
            String eventTime = splitDetailEvent[1].trim();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime eventDateTime = LocalDateTime.parse(eventTime, format);
            Event event = new Event(eventAction, eventDateTime);
            taskList.append(event);
            String eventMessage = "added: " + event.toString() + "\n";
            eventMessage += String.format("Now, you have %d task(s) in the list.", taskList.length());
            ui.print(eventMessage);
        }
        storage.saveTasks(taskList);
    }
}
