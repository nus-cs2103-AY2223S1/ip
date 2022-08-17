import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    ArrayList<Task> tasks = new ArrayList<>(100);

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
            } else if (cmd.split(" ", 2)[0].equals("mark")) {
                mark(Integer.parseInt(cmd.split(" ", 2)[1]) - 1);
            } else if (cmd.split(" ", 2)[0].equals("unmark")) {
                unmark(Integer.parseInt(cmd.split(" ", 2)[1]) - 1);
            } else {
                store(cmd);
            }
            cmd = sc.nextLine();
        }
    }

    public void store(String cmd) {
        Task task = new Task(cmd);
        tasks.add(tasks.size(), task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + task.getDescription());
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void list() {
        System.out.println("    ____________________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("     " + (i + 1) + task);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void mark(int index) {
        Task curr = tasks.get(index);
        curr.markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + curr);
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void unmark(int index) {
        Task curr = tasks.get(index);
        curr.unmarkTask();
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + curr);
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
