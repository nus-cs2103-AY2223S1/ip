import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<>();
    static Ui ui = new Ui();

    public static boolean doesCommandExist(String cmd) {
        return cmd.equals("list") || cmd.equals("mark")
                || cmd.equals("unmark") || cmd.equals("deadline") || cmd.equals("event")
                || cmd.equals("todo") || cmd.equals("delete") || cmd.equals("bye");
    }

    public static void detectCommand(CommandLine commandLine, String cmd) {
        try {
            switch (commandLine) {
                case BYE:
                    ui.exit();
                    break;
                case LIST:
                    ui.list(taskList);
                    break;
                case MARK:
                    Commands.mark(cmd, taskList);
                    break;
                case UNMARK:
                    Commands.unmark(cmd, taskList);
                    break;
                case DEADLINE:
                    Commands.deadline(cmd, taskList);
                    break;
                case EVENT:
                    Commands.event(cmd, taskList);
                    break;
                case TODO:
                    Commands.toDo(cmd, taskList);
                    break;
                case DELETE:
                    Commands.delete(cmd, taskList);
                    break;
                default:
                    ui.commandDoesNotExist();
                    break;
            }
        } catch (DukeException e) {
            ui.errorMessage(e);
        }
    }

    public static void main(String[] args) {
        ui.greeting();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        while (true) {
            String cmdLine = cmd.split(" ")[0];
            if (doesCommandExist(cmdLine)) {
                CommandLine commandLine = CommandLine.valueOf(cmdLine.toUpperCase());
                detectCommand(commandLine, cmd);
                if (commandLine == CommandLine.BYE) {
                    return;
                }
            } else {
                ui.commandDoesNotExist();
            }
            cmd = sc.nextLine();
        }
    }

    enum CommandLine {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE,
    }
}
