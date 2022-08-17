package duke;

public class ExitCommand extends Command{
    public void execute(Ui ui) {
        toggleExit();
        ui.showBye();
    }
}