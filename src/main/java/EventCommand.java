public class EventCommand extends Command {
    private String command;
    private TaskList tasks;
    private Ui ui;

    public EventCommand(String command, TaskList tasks, Ui ui) {
        this.command = command;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute() throws DukeException {
        String[] returnedArray = command.split(" /at ");
        if (returnedArray.length <= 0) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [event].");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your command is missing the [/at] component, or the second half ot the command." +
                    "\nPlease use the [help] command to check the proper usage of [event].");
        } else if (returnedArray.length > 2) {
            String secondHalf = "";
            for (int i = 1; i < returnedArray.length; i++) {
                secondHalf += returnedArray[i] + " ";
            }
            returnedArray[1] = secondHalf;
        }
        Event event = new Event(returnedArray[0], returnedArray[1]);
        tasks.add(event);
        ui.addTask(event, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
