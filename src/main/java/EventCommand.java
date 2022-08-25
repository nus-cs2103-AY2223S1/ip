import java.time.LocalDate;

public class EventCommand extends Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void run(TaskList taskList) throws DukeException{
        String[] segments = input.split("/at");
        if (segments.length != 2) {
            throw new DukeException("Error with event input");
        } else {
            String time = segments[1].strip();
            LocalDate date = LocalDate.parse(time);
            Event event = new Event(segments[0], date);
            taskList.createTask(event);
        }
    };
}
