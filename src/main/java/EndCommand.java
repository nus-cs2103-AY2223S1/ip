public class EndCommand extends Command {

    public static final String END = "Bye! Hope you had fun!";

    public EndCommand() {
        super();
    }

    @Override
    public boolean run() throws DukeException {
        Reply.printMessage(END);
        return true;
    }
}