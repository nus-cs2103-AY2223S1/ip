package duke.commands;

import duke.storage.Storage;
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
     * @param storage File to be saved to.
     */
    public void execute(TaskList taskList, Storage storage) {
        Event eventTask = new Event(eventDescription, eventAt);
        taskList.add(eventTask);
        System.out.println("Got it. I've added this task:\n" + eventTask
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        storage.save(taskList);
    }
}
