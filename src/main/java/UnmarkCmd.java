import java.util.ArrayList;

public class UnmarkCmd extends Command {
    private int num;
    public UnmarkCmd (String cmd, int num) {
        super(cmd);
        this.num = num;
    }

    @Override
    public void execute(String str, ArrayList<Task> tasks) throws DukeException {
        tasks.get(this.num).unmarkT();
        String out = "OK, I've marked this task as not done yet:\n  " +
                tasks.get(this.num).toString();
        System.out.println(out);
    }
}
