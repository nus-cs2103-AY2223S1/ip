import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends AddCommand {
    private AddEventCommand(String command, Event event) {
        super(command, event);
    }

    public static AddEventCommand of(String command) throws IllegalArgumentException {
        boolean isDone = command.contains("/done");
        if (isDone) {
            command = command.replace("/done", "");
        }
        String[] c2 = command.split("/at");
        String text = c2[0].replaceFirst("event", "").strip();
        String time = c2.length > 1 ? c2[1].strip() : "";
        if (text.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! The description of an event cannot be empty.\n");
        } else if (time.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a time for the event.\n");
        } else {
            LocalDateTime timeObj = null;
            try {
                timeObj = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("🙁 OOPS!!! Provide a valid time (dd/MM/yy HHmm) for the event.\n");
            }
            return new AddEventCommand(command, new Event(isDone, text, timeObj));
        }
    }
}
