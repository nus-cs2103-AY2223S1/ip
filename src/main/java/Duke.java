import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void handleCommands(Scanner sc) {
        String cmd = sc.nextLine();
        String sep = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        ArrayList<String> commands = new ArrayList<>();
        while (!cmd.equals("bye")) {
            if (!cmd.equals("list")) {
                commands.add(cmd);
                System.out.println(sep + "added: " + cmd + "\n" + sep);
            } else {
                System.out.println(sep);
                for(int i = 1; i <= commands.size(); i++) {
                    System.out.println(i + ". " + commands.get(i - 1) + "\n");
                }
                System.out.println(sep);
            }
            cmd = sc.nextLine();
        }
        System.out.println(sep + "byebye see you again :D" + "\n" + sep);
        sc.close();
    }

    public static void main(String[] args) {
        String logo =
                  " _              \n"
                + "| |    _   _ _____   ___ _  \n"
                + "| |   | | | |  __ \\ /     |\n"
                + "| |__ | |_| | |  | |    | |   \n"
                + "|____| \\__,_|_|  |_|\\__/|_|   \n";
        System.out.println("Hello. \nThis is \n" + logo + "\n How may I assist you today?");
        Scanner sc = new Scanner(System.in);
        handleCommands(sc);
    }
}
