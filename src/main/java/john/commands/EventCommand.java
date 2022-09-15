package john.commands;

/**
 * Represents a command to create an event task.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String FORMAT = "event <description> /at <dd/mm/yyyy> <hhmm | optional>";

    private String description;
    private String timing;

    /**
     * Constructor for an EventCommand.
     *
     * @param params The parameters of the event, including the description and timing.
     */
    public EventCommand(String params) {
        String[] eventParams = params.split(" /at ", 2);
        this.description = eventParams[0];
        this.timing = eventParams[1];
    }

    /**
     * Adds an event task to the task list.
     *
     * @return A string representation of the event added.
     */
    @Override
    public String execute() {
        String task = tasklist.addEvent(description, timing);
        assert task != null : "task cannot be null!";
        return ui.showAddedTask(task, tasklist);
    }
}
