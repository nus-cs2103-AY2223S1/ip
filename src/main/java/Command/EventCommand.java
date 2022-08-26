import java.io.IOException;

/**
 * Adds an event to the tasklist
 */
public class EventCommand extends Command {
    boolean exit;
    private String userAction;
    public EventCommand(String userAction) {
        this.exit = false;
        this.userAction = userAction;
    }

    @Override
    public void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            String[] eventString = userAction.split("/at ");
            if (eventString[0].equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (eventString.length == 1) {
                throw new DukeException("The description or date/time of an event cannot be empty.");
            } else {
                Task newEvent = new Event(eventString[0], eventString[1]);
                tasks.addTask(newEvent);
                storage.save();
                DukeUi.sendMessage(" Got it. I've added this task:\n" + "   " + newEvent.toString()
                        + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
            }
        } catch (IOException | DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
