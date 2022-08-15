import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private static final String LOGO =
              "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
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
        reply(LOGO + "\n     Hello! I'm Duke\n     What can I do for you?");
    }

    public void reply(String msg) {
        String response = HORIZONTAL_LINE
                + msg + "\n"
                + HORIZONTAL_LINE;
        System.out.println(response);
    }

    public String add(Commands type, StringBuilder stringBuilder) {
        String[] arguments;
        Task newTask = new Task("");
        int number = 0;
        try {
            switch (type) {
                case TODO:
                    String input = sc.nextLine();
                    if (input.length() < 1) {
                        throw new DukeException("Something went wrong! Could not read TODO.");
                    }
                    newTask = new ToDo(input);
                    number = this.taskList.addTask(newTask);
                    break;
                case DEADLINE:
                    arguments = sc.nextLine().split("/by");
                    if (arguments.length < 2) {
                        throw new DukeException("     Something went wrong! Could not read DEADLINE.");
                    }
                    newTask = new Deadline(arguments[0], arguments[1]);
                    number = this.taskList.addTask(newTask);
                    break;
                case EVENT:
                    arguments = sc.nextLine().split("/at");
                    if (arguments.length < 2) {
                        throw new DukeException("     Something went wrong! Could not read EVENT.");
                    }
                    newTask = new Event(arguments[0], arguments[1]);
                    number = this.taskList.addTask(newTask);
                    break;
            }
            stringBuilder.append("     Got it. I've added this task:\n     --> ");
            stringBuilder.append(newTask);
            stringBuilder.append(String.format("\n     Now you have %d tasks in the list.", number));
            return stringBuilder.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public void commandHandler() {
        while (sc.hasNext()) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                String input = sc.next();
                if (!commandMap.containsKey(input)) {
                    throw new DukeException("     I'm sorry, but I don't understand that.");
                }
                Commands type = commandMap.get(input);
                switch (type) {
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        reply(add(type, stringBuilder));
                        break;
                    case LIST:
                        reply(this.taskList.toString());
                        break;
                    case MARK:
                        stringBuilder.append("     Nice! I've marked this task as done:\n     ");
                        stringBuilder.append(this.taskList.markDone(sc.nextInt() - 1));
                        reply(stringBuilder.toString());
                        break;
                    case UNMARK:
                        stringBuilder.append("     OK, I've marked this task as not done yet:\n     ");
                        stringBuilder.append(this.taskList.unmarkDone(sc.nextInt() - 1));
                        reply(stringBuilder.toString());
                        break;
                    case BYE:
                        reply("     Bye. Hope to see you again soon!");
                        sc.close();
                        break;
                }
                if (type == Commands.BYE) {
                    break;
                }
            } catch (DukeException e) {
                reply(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.commandHandler();
    }
}
