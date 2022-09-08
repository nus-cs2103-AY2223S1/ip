package chad;

import java.io.IOException;
import java.util.ArrayList;

import chad.exception.ChadException;
import chad.task.Task;

/**
 * Contains commands for user to interact with Chadbot
 */
public class Parser {
    /**
     * Reads user input in command line
     * @param taskList arraylist of tasks
     * @param userInput user input
     * @throws ChadException Thrown when invalid error occurs
     * @throws IOException Thrown when helper file cannot be open
     */
    public static String readUserInput(ArrayList<Task> taskList, String userInput) {
        String[] tempArr = userInput.split(" ");
        String keyword = tempArr[0];

        try {
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
            default:
                return "I'm sorry, but I don't know what that means :-(";
//                throw new ChadException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (ChadException ce) {
            return ce.getMessage();
        }
    }
}
