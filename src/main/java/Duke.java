import duke.command.DukeCommandType;
import duke.task.DukeTaskManager;
import duke.ui.DukePrinter;

import java.util.Scanner;

public class Duke {

    private static Scanner sc;
    private static DukeTaskManager taskManager;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startService();
    }

    private static void startService() {
        DukePrinter.dukePrint("Hello! I'm Duke\nWhat can I do for you?\n");
        sc = new Scanner(System.in);
        taskManager = new DukeTaskManager(SaveManager.loadData());
        run();
    }

    private static void run() {
        while (sc.hasNextLine()) {
            String str = sc.nextLine().replaceAll("( )+", " ");
            String command = str.split(" ")[0];
            DukeCommandType commandType = getCommandType(command);
            String args = str.replaceFirst(command, "").trim();
            switch (commandType) {
            case EXIT:{
                endService();
                return;
            }
            case LIST: {
                taskManager.dukeShowList();
                break;
            }
            case MARK:
            case UNMARK:
            case DELETE: {
                taskManager.dukeUpdateTaskStatus(commandType, args);
                break;
            }
            case TODO:
            case EVENT:
            case DEADLINE: {
                taskManager.dukeAddTask(commandType, args);
                break;
            }
            default: {
                DukePrinter.dukePrint("Invalid Command. Please try again\n");
            }
            }
        }
    }

    private static void endService() {
        DukePrinter.dukePrint("Bye. Hope to see you again!\n");
        SaveManager.saveData(taskManager.getTasks());
        sc.close();
        return;
    }

    private static DukeCommandType getCommandType(String command) {
        switch(command) {
        case "exit":
        case "quit":
            //Fallthrough
        case "bye":
            return DukeCommandType.EXIT;
        case "list":
            return DukeCommandType.LIST;
        case "mark":
            return DukeCommandType.MARK;
        case "unmark":
            return DukeCommandType.UNMARK;
        case "delete":
        case "remove":
            return DukeCommandType.DELETE;
        case "todo":
            return DukeCommandType.TODO;
        case "deadline":
            return DukeCommandType.DEADLINE;
        case "event":
            return DukeCommandType.EVENT;
        default:
            return DukeCommandType.UNKNOWN;
        }
    }
}
