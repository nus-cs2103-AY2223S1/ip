import java.util.Scanner;

public class Echo {

    public Echo() {}

    private static Scanner sc = new Scanner(System.in);

    public static void echo (Tasks[] tasks) {
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
        } else {
            cmd = new AddCmd(str);
            cmd.execute(str, tasks);
        }

        echo(tasks);
    }
}
