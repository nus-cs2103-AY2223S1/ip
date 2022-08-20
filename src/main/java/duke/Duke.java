package duke;

import duke.command.Command;
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
        dukeUi = new DukeUi();
        dukeUi.dukePrint("Hello! I'm Duke \nWhat can I do for you?\n");
        taskManager = new DukeTaskManager(Storage.loadData());
        run();
    }

    private static void run() {
        while(dukeUi.hasNextLine()) {
            Command command = Parser.getCommand(dukeUi.getNextLine());
            DukeCommandType commandType = command.getCommandType();
            String args = command.getArgs();
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
}
