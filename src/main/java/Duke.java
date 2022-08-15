import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private final Scanner sc;
    private TaskList taskList;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        reply(logo + "\n     Hello! I'm Duke"
                + "\n     What can I do for you?");
    }

    public void reply(String msg) {
        String response = HORIZONTAL_LINE
                + msg + "\n"
                + HORIZONTAL_LINE;
        System.out.println(response);
    }

    public void add(String description) {
        this.taskList.addTask(new Task(description));
        reply("     added: " + description);
    }

    public void commandHandler() {
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.compareTo("bye") == 0) {
                reply("     Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (command.compareTo("list") == 0) {
                reply(this.taskList.toString());
            } else if (command.compareTo("mark") == 0) {
                reply("     Nice! I've marked this task as done:\n     " + this.taskList.markDone(sc.nextInt() - 1));
            } else if (command.compareTo("unmark") == 0) {
                reply("     OK, I've marked this task as not done yet:\n     " + this.taskList.unmarkDone(sc.nextInt() - 1));
            } else {
                add(sc.nextLine());
            }
        }
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.commandHandler();
    }
}
