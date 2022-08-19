import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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

    static ArrayList<Task> taskList = new ArrayList<>();
    static Ui ui = new Ui();

    public static boolean doesCommandExist(String cmd) {
        return cmd.equals("list") || cmd.equals("mark")
                || cmd.equals("unmark")  || cmd.equals("deadline")  || cmd.equals("event")
                || cmd.equals("todo")  || cmd.equals("delete") ;
    }
    public static void detectCommand(CommandLine commandLine, String cmd) {
        switch (commandLine) {
            case LIST:
                ui.list(taskList);
                break;
            case MARK:
                try {
                    Commands.mark(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
                break;
            case UNMARK:
                try {
                    Commands.unmark(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
                break;
            case DEADLINE:
                try {
                    Commands.deadline(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
                break;
            case EVENT:
                try {
                    Commands.event(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
                break;
            case TODO:
                try {
                    Commands.toDo(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
                break;
            case DELETE:
                try {
                    Commands.delete(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
                break;
            default:
                ui.commandDoesNotExist();
                break;
        }
    }

    public static void main(String[] args) {
        ui.greeting();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        while (!cmd.equals("bye")){
            String cmdLine = cmd.split(" ")[0];
            if (doesCommandExist(cmdLine)) {
                CommandLine commandLine = CommandLine.valueOf(cmdLine.toUpperCase());
                detectCommand(commandLine, cmd);
            } else {
                ui.commandDoesNotExist();
            }
            cmd = sc.nextLine();
        }
        ui.exit();
    }
}
