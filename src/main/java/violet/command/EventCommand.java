package violet.command;

import violet.exception.VioletException;
import violet.TaskList;
import violet.Ui;
import violet.task.Event;

/**
 * EventCommand class to execute the event command.
 */
public class EventCommand extends Command {
    /** The input given by the user. */
    private String input;

    /**
     * Instantiates the EventCommand object.
     *
     * @param input The input given by the user.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws VioletException {
        String[] eventArray = input.split("event", 2);
        String taskAt = eventArray[1];
        String[] taskEvent = taskAt.split("/at", 2);

        if (eventArray[1].isEmpty()) {
            throw new VioletException("Please give me the description of the event.\n");
        }

        if (taskEvent.length < 2 || taskEvent[1].isEmpty()) {
            throw new VioletException("Please include an /at for your deadline. "
                    + "E.g /at 2019-10-15T10:15:00.\n");
        } else {
            Event e = new Event(taskEvent[0].substring(1), taskEvent[1]);
            int index = taskList.size() + 1;
            taskList.add(e);
            this.response = "Got it. I've added this task:\n" + e.toString()
                    + "\n" + "Now you have " + index + " tasks in this list.";
        }
    }
}
