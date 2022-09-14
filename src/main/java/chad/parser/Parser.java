package chad.parser;

import java.util.ArrayList;

import chad.exception.ChadException;
import chad.task.Task;
import chad.task.TaskList;
import chad.ui.Ui;

/**
 * Contains commands for user to interact with Chadbot
 *
 */
public class Parser {
    /**
     * Reads user input in command line
     *
     * @param taskList arraylist of tasks
     * @param userInput user input
     * @throws ChadException throws error when invalid command is input
     */
    public static String readCommand(ArrayList<Task> taskList, String userInput) throws ChadException {
        String[] tempArr = userInput.split(" ");
        String keyword = tempArr[0];

        switch (keyword) {
        case "bye": {
            return Ui.closeChat();
        }
        case "list": {
            return Ui.listTask(taskList);
        }
        case "mark": {
            int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
            return Ui.markTask(taskList, taskID);
        }
        case "unmark": {
            int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
            return Ui.unmarkTask(taskList, taskID);
        }
        case "delete": {
            int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
            return TaskList.deleteTask(taskList, taskID);
        }
        case "date": {
            return Ui.printTaskAtDate(taskList, userInput);
        }
        case "todo": {
            return TaskList.addTodoTask(taskList, userInput);
        }
        case "deadline": {
            return TaskList.addDeadlineTask(taskList, userInput);
        }
        case "event": {
            return TaskList.addEventTask(taskList, userInput);
        }
        case "find": {
            return Ui.searchTaskByKeyword(taskList, userInput);
        }
        case "help": {
            return Ui.helpCommands();
        }
        case "archive": {
            return TaskList.archiveTasks(taskList);
        }
        default:
            throw new ChadException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
