import java.util.Scanner;

public class Parser {



    public void command() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                list();
            } else if (cmd.split(" ")[0].equals("mark")) {
                mark(cmd);
            } else if (cmd.split(" ")[0].equals("unmark")) {
                unmark(cmd);
            } else if (cmd.split(" ")[0].equals("delete")) {
                delete(cmd);
            } else {
                addTask(cmd);
            }
            cmd = sc.nextLine();
        }
        sc.close();
    }

}
