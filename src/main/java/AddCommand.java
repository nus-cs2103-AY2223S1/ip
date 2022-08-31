public class AddCommand extends Command {

    private String keyword;
    private String input;

    public AddCommand(String keyword, String commandInput) {
        this.keyword = keyword;
        this.input = commandInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (this.input.isBlank() || this.input.isBlank()) {
                throw new DukeException("oops, the description of your task seems to be incomplete!");
            }
            tasks.addTask(this.keyword, this.input);
            storage.writeToFile(this.keyword + "," + this.input);
            ui.showAddedTask(tasks);
            ui.showWrittenTask();
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
