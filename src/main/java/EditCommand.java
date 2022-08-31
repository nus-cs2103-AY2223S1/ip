public class EditCommand extends Command {

    private String keyword;
    private String input;

    public EditCommand(String keyword, String input) {
        this.keyword = keyword;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(this.keyword, this.input, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
