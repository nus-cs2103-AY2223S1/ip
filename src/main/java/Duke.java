import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String sep = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static Scanner sc;

    public static void bye() {
        System.out.println(sep + "byebye see you again :D" + "\n" + sep);
        sc.close();
    }

    public static void mark(String cmd) {
        int num = Integer.parseInt(cmd.substring(cmd.length() - 1));
        Task curr = Task.tasks.get(num - 1);
        curr.mark();
    }

    public static void unmark(String cmd) {
        int num = Integer.parseInt(cmd.substring(cmd.length() - 1));
        Task curr = Task.tasks.get(num - 1);
        curr.unmark();
    }

    public static void print(Task task) {
        if (Task.tasks.size() == 1) {
            System.out.println(sep + "\nAdded:\n" + task.toString() + "\nThere is only " + Task.tasks.size() + " task in your list!\n" + sep);
        } else {
            System.out.println(sep + "\nAdded:\n" + task.toString() + "\nThere are currently " + Task.tasks.size() + " tasks in your list!\n" + sep);
        }
    }

    public static void handleCommands(Scanner sc) {
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                Task.list();
            } else if (cmd.startsWith("mark")) {
                mark(cmd);
            } else if (cmd.startsWith("unmark")) {
                unmark(cmd);
            } else if (cmd.startsWith("todo")) {
                Task curr = new Todo(cmd.substring(5));
                print(curr);
            } else if (cmd.startsWith("deadline")) {
                String[] cmds = cmd.split(" /by ");
                Task curr = new Deadline(cmds[0], cmds[1]);
                print(curr);
            } else if (cmd.startsWith("event")) {
                String[] cmds = cmd.split(" /at ");
                Task curr = new Event(cmds[0], cmds[1]);
                print(curr);
            }
            cmd = sc.nextLine();
        }
        bye();
    }

    public static void main(String[] args) {
        String logo =
                  "    _              \n"
                + "   | |    _   _ _____   ___ _  \n"
                + "   | |   | | | |  __ \\ /     |\n"
                + "   | |__ | |_| | |  | |    | |   \n"
                + "   |____| \\__,_|_|  |_|\\__/|_|   \n";
        System.out.println("Hello. \n   This is \n" + logo + "\n   How may I assist you today?");
        sc = new Scanner(System.in);
        Task.tasks = new ArrayList<Task>();
        handleCommands(sc);
    }
}
