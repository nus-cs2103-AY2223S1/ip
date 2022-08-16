import java.util.ArrayList;

public class ExitCmd extends Command {

    public ExitCmd(String cmd) {
        super(cmd);
    }

    @Override
    public void execute (String str, ArrayList<Task> tasks) throws DukeException {
        String out = "Bye. Hope to see you again soon!";
        System.out.println(out);
        System.exit(0);
    }
}
