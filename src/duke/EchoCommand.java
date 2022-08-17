package duke;

public class EchoCommand extends Command {

    private String echo;
    public EchoCommand(String echo) {
        this.echo = echo;
    }
    public void execute(Ui ui) {
        ui.echoCommand(this.echo);
    }
}