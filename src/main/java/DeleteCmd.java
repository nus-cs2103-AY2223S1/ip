import java.util.ArrayList;

public class DeleteCmd extends Command {
    private int num;
    public DeleteCmd (String cmd, int num) {
        super(cmd);
        this.num = num;
    }

    @Override
    public void execute(String str, ArrayList<Task> tasks) throws DukeException {

        String info = tasks.get(this.num).toString();
        String plural = tasks.size() - 1 <= 1
                ? "task"
                : "tasks";
        tasks.remove(this.num);
        String out = "Noted. I've removed this task:\n  " +
                info + "\n Now you have " + tasks.size() +
                " " + plural + " in the list";
        System.out.println(out);
    }
}
