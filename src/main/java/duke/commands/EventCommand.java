package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Event;
import duke.exception.DukeException;
import duke.exception.InvalidDescriptionCommand;
import duke.exception.DuplicateException;

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
     *
     * @param taskList List where an event is to be added to it.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String[] splitDetailEvent = this.input.split("/at", 2);
        if (splitDetailEvent.length == 1) {
            throw new InvalidDescriptionCommand(true);
        } else {
            String eventAction = splitDetailEvent[0].trim();
            String eventTime = splitDetailEvent[1].trim();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime eventDateTime = LocalDateTime.parse(eventTime, format);
            Event event = new Event(eventAction, eventDateTime);
            if (taskList.detectDuplicate(event)) {
                throw new DuplicateException();
            } else {
                taskList.append(event);
                String eventMessage = "added: " + event.toString() + "\n";
                storage.saveTasks(taskList);
                return ui.print(eventMessage, taskList);
            }
        }
    }
}
