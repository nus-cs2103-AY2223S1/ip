import java.util.ArrayList;

public class Commands {
    protected static Ui ui = new Ui();

    public static void mark(String cmd, ArrayList<Task> taskList) {
        String number = cmd.split(" ")[1];
        int num = Integer.parseInt(number);
        Task task = taskList.get(num - 1);
        task.markAsDone();
        ui.marked(task);
    }

    public static void dontExist(String cmd) throws DukeException {
        System.out.println(cmd);
    }
}
