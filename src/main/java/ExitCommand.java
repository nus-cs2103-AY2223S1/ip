public class ExitCommand extends Command{

    public ExitCommand(){}

    @Override
    void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit(){
        return this.isExit = true;
    }
}
