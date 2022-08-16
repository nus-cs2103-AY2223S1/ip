import java.util.ArrayList;
import java.util.Scanner;

public class Echo {

    public Echo() {}

    private static int cnt = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void echo (ArrayList<Task> tasks) throws DukeException {
        String str = sc.nextLine();
        Command cmd;
        if (str.equals("bye")) {
            cmd = new ExitCmd(str);
            cmd.execute(str, tasks);
        } else if (str.equals("list")) {
            cmd = new ListCmd(str);
            cmd.execute(str, tasks);
        } else if (str.startsWith("mark ")) {
            String subStr = str.substring(5, str.length());
            int num = Integer.parseInt(subStr);
            cmd = new MarkCmd(str, num - 1);
            cmd.execute(str, tasks);
        } else if (str.startsWith("unmark ")) {
            String subStr = str.substring(7, str.length());
            int num = Integer.parseInt(subStr);
            cmd = new UnmarkCmd(str, num - 1);
            cmd.execute(str, tasks);
        } else if (str.startsWith("delete")) {
            if (str.length() <= "delete ".length()) {
                throw new DukeException("The index of a task cannot be empty.");
            }
            String subStr = str.substring(7, str.length());
            int num = Integer.parseInt(subStr);
            cmd = new DeleteCmd(str, num - 1);
            cmd.execute(str, tasks);
        } else {
            cnt ++;
            cmd = new AddCmd(str);
            cmd.execute(str, tasks);
        }

        echo(tasks);
    }
}
