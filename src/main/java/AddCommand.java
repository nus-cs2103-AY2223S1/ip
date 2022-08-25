public class AddCommand implements Command{
    String[] inputs;
    String commandType;

    public AddCommand(String[] inputs) {
        this.inputs = inputs;
        commandType = inputs[0].toUpperCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch(commandType) {
            case "TODO":
                task = new ToDo(Parser.getDescription(inputs, null, false), false);
                break;
            case "DEADLINE":
                task = new Deadline(Parser.getDescription(inputs, "/by", true),
                        false,
                        Parser.getTime(inputs, "/by"));
                break;
            case "EVENT":
                task = new Deadline(Parser.getDescription(inputs, "/at", true),
                        false,
                        Parser.getTime(inputs, "/at"));
                break;
            default:
                throw new DukeException();
        }
        Ui.dukePrint(tasks.add(task));
        storage.addTaskToStorage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
