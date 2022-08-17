import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    ArrayList<String> tasks = new ArrayList<>(100);

    public Duke() {

    }

    public void run() {
        welcome();
        command();
        bye();
    }

    public void welcome() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________");
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void command() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                list();
            } else {
                store(cmd);
            }
            cmd = sc.nextLine();
        }
    }

    public void store(String cmd) {
        tasks.add(tasks.size(), cmd);
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + cmd);
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void list() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!" );
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke program = new Duke();
        program.run();
    }
}
