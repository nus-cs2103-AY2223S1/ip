package duke.commands;

import duke.task.Event;
import duke.task.TaskList;

import java.util.Scanner;

public class EventWithStatusCommand extends Command {
    private final String eventDescription;
    private final String eventAt;
    private final boolean isDone;

    public EventWithStatusCommand(Scanner scanner) {
        eventDescription = scanner.next().strip();
        eventAt = scanner.next().strip();
        isDone = Boolean.parseBoolean(scanner.reset().skip("\\|").nextLine().strip());
    }

    public String execute(TaskList taskList) {
        Event eventTask = new Event(eventDescription, eventAt, isDone);
        taskList.add(eventTask);
        return "Success";
    }
}
