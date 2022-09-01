package duke;

import javafx.application.Platform;
import javafx.stage.Stage;

import duke.command.Command;
import duke.command.DukeCommandType;
import duke.task.DukeTaskManager;
import duke.ui.DukeUi;

/**
 * Duke is a Personal Assistant Chatbot that helps users to organise their tasks using a command line interface
 */
public class Duke {

    private static DukeTaskManager taskManager;
    private static DukeUi dukeUi;

    private static Stage stage;

    private static final String START_MESSAGE = "Hello! I am Duke. \nWhat can I do for you? \n";

    public void setStage(Stage stage) {
        Duke.stage = stage;
    }
    /**
     * Starts the process of running Duke
     */
    public String startService() {
        dukeUi = new DukeUi();
        taskManager = new DukeTaskManager(Storage.loadData());
        return START_MESSAGE;
    }


    private static void endService() {
        Storage.saveData(taskManager.getTasks());
        dukeUi.dukePrint("Bye! Hope to see you again!");
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                Platform.runLater(() -> stage.close());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Command command = Parser.getCommand(input);
        DukeCommandType commandType = command.getCommandType();
        String args = command.getArgs();
        switch (commandType) {
        case EXIT: {
            endService();
            break;
        }
        case LIST: {
            taskManager.dukeShowAllTasks();
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
        case FIND: {
            taskManager.dukeFindTasksContaining(args);
            break;
        }
        default: {
            dukeUi.dukePrint("Invalid Command. Please try again\n");
        }
        }
        return dukeUi.getResponse();
    }

}
