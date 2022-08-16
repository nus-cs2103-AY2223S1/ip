import java.util.Scanner;
import java.util.ArrayList;

public class Luna {
    private static ArrayList<Task> tasks;
    private static String sep = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static Scanner sc;

    public static void bye() {
        System.out.println(sep + "byebye see you again :D" + "\n" + sep);
        sc.close();
    }

    public static void list() {
        System.out.println(sep);
        System.out.println("Stuff you have to do!\n");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i) + "\n");
        }
        System.out.println(sep);
    }

    public static void mark(String cmd) {
        int num = Integer.parseInt(cmd.substring(cmd.length() - 1));
        Task curr = tasks.get(num - 1);
        System.out.println(sep);
        curr.mark();
        System.out.println("\n" + sep);
    }

    public static void unmark(String cmd) {
        int num = Integer.parseInt(cmd.substring(cmd.length() - 1));
        Task curr = tasks.get(num - 1);
        System.out.println(sep);
        curr.unmark();
        System.out.println("\n" + sep);
    }

    public static void add(String cmd) {
        Task task = new Task(cmd);
        tasks.add(task);
        System.out.println(sep + "added: " + cmd + "\n" + sep);
    }

    public static void handleCommands(Scanner sc) {
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                list();
            } else if (cmd.startsWith("mark")) {
                mark(cmd);
            } else if (cmd.startsWith("unmark")) {
                unmark(cmd);
            } else {
                add(cmd);
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
        tasks = new ArrayList<>();
        handleCommands(sc);
    }
}
