package duke.command;

import duke.task.Event;
import duke.task.TasksController;
import duke.Ui;
import duke.Storage;
import duke.exception.EmptyContentException;
import duke.exception.InvalidTimeException;
/**
 * CreatEventCommand will execute the command of creating a new event.
 */
public class CreateEventCommand extends Command {

    /**
     * Creates a new event
     * @param controller Duke task controller
     * @param ui Duke UI
     * @param storage Duke IO processor
     */
    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.prompt("Please type in your Event:");
        String event = ui.inputText();
        try {
            controller.checkTaskContent(event);
            ui.prompt("Please type in time for your event (yyyy-mm-dd HH:MM):");
            String eventTime = ui.inputText();
            controller.checkTimeFormat(eventTime);
            Event eventTask = new Event(event, eventTime);
            controller.addToList(eventTask);
            ui.display(eventTask.toString(), false, false, false, false, false);
        } catch (EmptyContentException ece) {
            ui.reportError("No empty task is allowed! Please try again...");
            ui.showSplitLine();
        } catch (InvalidTimeException ite) {
            ui.reportError("The time format is invalid! Please try again...");
            ui.showSplitLine();
        }

    }
}
