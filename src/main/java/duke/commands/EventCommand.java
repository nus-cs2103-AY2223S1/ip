package duke.commands;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;

import java.util.Scanner;

public class EventCommand extends Command {
    private final String eventDescription;
    private final String eventAt;

    public EventCommand(Scanner scanner) {
        scanner.useDelimiter("/at");
        eventDescription = scanner.next().strip();
        scanner.reset().skip("/at");
        eventAt = scanner.nextLine().strip();
    }

    public void execute(TaskList taskList, Storage storage) {
        Event eventTask = new Event(eventDescription, eventAt);
        taskList.add(eventTask);
        System.out.println("Got it. I've added this task:\n" + eventTask
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        storage.save(taskList);
    }
}
