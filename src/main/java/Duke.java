import java.util.Scanner;

public class Duke {



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
            echo(cmd);
            cmd = sc.nextLine();
        }
    }

    public void echo(String cmd) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + cmd);
        System.out.println("    ____________________________________________________________");
        System.out.println();
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
