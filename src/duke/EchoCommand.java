package duke;

public class EchoCommand extends Command {

    private String echo;
    public EchoCommand(String echo) {
        this.echo = echo;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.echoCommand(this.echo);
    }
}