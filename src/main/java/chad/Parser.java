package chad;

import chad.Ui;
import chad.Utility;
import chad.exception.ChadException;
import chad.task.Task;

import java.io.IOException;
import java.util.ArrayList;

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
    public static void readUserInput(ArrayList<Task> taskList, String userInput) throws ChadException, IOException {
        String[] tempArr = userInput.split(" ");
        String keyword = tempArr[0];

        try {
            switch (keyword) {
            case "bye": {
                Ui.closeChat();
                break;
            }
            case "list": {
                Ui.listTask(taskList);
                break;
            }
            case "mark": {
                int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Ui.markTask(taskList, taskID);
                break;
            }

            case "unmark": {
                int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Ui.unmarkTask(taskList, taskID);
                break;
            }
            case "delete": {
                int taskID = Integer.parseInt(userInput.split(" ")[1]) - 1;
                TaskList.deleteTask(taskList, taskID);
                break;
            }
            case "date": {
                Ui.printTaskAtDate(taskList, userInput);
                break;
            }
            case "todo": {
                TaskList.addTodoTask(taskList, userInput);
                break;
            }
            case "deadline": {
                TaskList.addDeadlineTask(taskList, userInput);
                break;
            }
            case "event": {
                TaskList.addEventTask(taskList, userInput);
                break;
            }
            default: {
                throw new ChadException("I'm sorry, but I don't know what that means :-(");
            }
            }
        } catch (ChadException ce) {
            Utility.printText(ce.getMessage());
        }
    }
}
