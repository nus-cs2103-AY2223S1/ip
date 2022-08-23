package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.tasks.*;

/**
 * The EventCommand class encapsulates the execution of an event command.
 */
public class EventCommand extends Command{
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the event command.
     * @param taskList List where an event is to be added to it.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command.
     * @throws DukeException
     * @throws IOException
     */
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
            ui.print(eventMessage, taskList);
        }
        storage.saveTasks(taskList);
    }
}
