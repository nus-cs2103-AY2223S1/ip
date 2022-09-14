package duke.commands;

import duke.task.Event;
import duke.task.TaskList;

import java.util.Scanner;

public class EventCommand extends Command {
    private final String eventDescription;
    private final String eventAt;

    /**
     * Parse the command from scanner and store the description as well as date for event task.
     *
     * @param scanner User input.
     */
    public EventCommand(Scanner scanner) {
        scanner.useDelimiter("/at");
        eventDescription = scanner.next().strip();
        scanner.reset().skip("/at");
        eventAt = scanner.nextLine().strip();
    }

    /**
     * Create new event task and store it in the task list. Finally, save the task list in storage.
     *
     * @param taskList Task list that stores new event task.
     */
    public String execute(TaskList taskList) {
        Event eventTask = new Event(eventDescription, eventAt);
        taskList.add(eventTask);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in your list.",
                eventTask, taskList.getSize());
    }
}
