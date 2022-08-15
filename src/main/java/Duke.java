import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private final Scanner sc;
    private TaskList taskList;
    private enum Commands {
        TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK
    }
    private HashMap<String, Commands> commandMap = new HashMap<>(Map.of(
            "todo", Commands.TODO,
            "deadline", Commands.DEADLINE,
            "event", Commands.EVENT,
            "list", Commands.LIST,
            "bye", Commands.BYE,
            "mark", Commands.MARK,
            "unmark", Commands.UNMARK
    ));

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

    public void add(Commands type) {
        String regex;
        String[] arguments;
        Task newTask = new Task("");
        int number = 0;
        switch (type) {
            case TODO:
                newTask = new ToDo(sc.nextLine());
                number = this.taskList.addTask(newTask);
                break;
            case DEADLINE:
                arguments = sc.nextLine().split("/by");
                newTask = new Deadline(arguments[0], arguments[1]);
                number = this.taskList.addTask(newTask);
                break;
            case EVENT:
                arguments = sc.nextLine().split("/at");
                newTask = new Event(arguments[0], arguments[1]);
                number = this.taskList.addTask(newTask);
                break;
        }
        reply("     Got it. I've added this task:\n     " + newTask + String.format("\n     Now you have %d tasks in the list.", number));
    }

    public void commandHandler() {
        while (sc.hasNext()) {
            Commands type = commandMap.get(sc.next());
            switch (type) {
                case TODO:
                case DEADLINE:
                case EVENT:
                    add(type);
                    break;
                case LIST:
                    reply(this.taskList.toString());
                    break;
                case MARK:
                    reply("     Nice! I've marked this task as done:\n     " + this.taskList.markDone(sc.nextInt() - 1));
                    break;
                case UNMARK:
                    reply("     OK, I've marked this task as not done yet:\n     " + this.taskList.unmarkDone(sc.nextInt() - 1));
                    break;
                case BYE:
                    reply("     Bye. Hope to see you again soon!");
                    sc.close();
                    break;
            }
            if (type == Commands.BYE) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.commandHandler();
    }
}
