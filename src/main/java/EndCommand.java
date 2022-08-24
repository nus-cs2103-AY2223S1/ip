public class EndCommand extends Command {

    public static final String END = "Bye! Hope you had fun!";

    public EndCommand() {
        super();
    }

    @Override
    public String run() throws DukeException {
        return END;
    }
}