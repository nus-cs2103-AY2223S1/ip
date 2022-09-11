package duke;

/**
 * EventCommand is a Command that adds an Event task to the TaskList.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Constructor for EventCommand.
     *
     * @param input input from user.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the EventCommand and returns output to user.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            // Error checking
            if (input.length() == 5 || input.substring(6).equals("0")) {
                throw new EmptyDescriptionException("OOPS!!! The description of a event cannot be empty.");
            }
            //  Isolates remaining input after "event" and filters further for task description and time
            String remainder = input.substring(6);
            String[] arr = remainder.split("/at");
            String description = arr[0].trim();
            String time = arr[1].trim();

            Event event = new Event(description, time);
            taskList.addTask(event);
            storage.updateStorage(taskList);
            return ui.addResponse(event, taskList);
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }
}
