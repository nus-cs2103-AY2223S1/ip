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
            } else if (cmd.split(" ")[0].equals("mark")) {
                mark(Integer.parseInt(cmd.split(" ")[1]) - 1);
            } else if (cmd.split(" ")[0].equals("unmark")) {
                unmark(Integer.parseInt(cmd.split(" ")[1]) - 1);
            } else {
                addTask(cmd);
            }
            cmd = sc.nextLine();
        }
        sc.close();
    }

    public void addTask(String cmd) {
        String type = cmd.split(" ")[0];
        switch (type) {
            case "todo":
                addTodo(cmd);
                break;

            case "deadline":
                addDeadline(cmd);
                break;

            case "event":
                addEvent(cmd);
                break;
        }
        Task task = tasks.get(tasks.size() - 1);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void addTodo(String cmd) {
        String desc = cmd.split(" ", 2)[1];
        Todo todo = new Todo(desc);
        tasks.add(tasks.size(), todo);
    }

    public void addDeadline(String cmd) {
        String[] div = cmd.split("/");
        String desc = div[0].split(" ", 2)[1];
        String by = div[1].split(" ", 2)[1];
        Deadline deadline = new Deadline(desc, by);
        tasks.add(tasks.size(), deadline);
    }

    public void addEvent(String cmd) {
        String[] div = cmd.split("/");
        String desc = div[0].split(" ", 2)[1];
        String at = div[1].split(" ", 2)[1];
        Event event = new Event(desc, at);
        tasks.add(tasks.size(), event);
    }

    public void list() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("     " + (i + 1) + "." + task);
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
