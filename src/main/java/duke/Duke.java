package duke;

import duke.command.DukeCommandType;
import duke.task.DukeTaskManager;
import duke.ui.DukeUi;


public class Duke {

    private static DukeTaskManager taskManager;
    private static DukeUi dukeUi;

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
        dukeUi.dukePrint("Hello! I'm Duke \nWhat can I do for you?\n");
        dukeUi = new DukeUi();
        taskManager = new DukeTaskManager(Storage.loadData());
        run();
    }

    private static void run() {
        while(dukeUi.hasNextLine()) {
            String str = dukeUi.getNextLine().replaceAll("( )+", " ");
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
                //Fallthrough
            case DELETE: {
                taskManager.dukeUpdateTaskStatus(commandType, args);
                break;
            }
            case TODO:
            case EVENT:
                //Fallthrough
            case DEADLINE: {
                taskManager.dukeAddTask(commandType, args);
                break;
            }
            default: {
                dukeUi.dukePrint("Invalid Command. Please try again\n");
            }
            }
        }
    }

    private static void endService() {
        dukeUi.dukePrint("Bye. Hope to see you again!\n");
        Storage.saveData(taskManager.getTasks());
        dukeUi.endService();
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
