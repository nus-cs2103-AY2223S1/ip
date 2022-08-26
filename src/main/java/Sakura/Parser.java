package Sakura;

import java.io.IOException;

/**
 * Represents a parser for the user input
 */
public class Parser {
    /**
     * Takes in user input and executes further actions depending on the command word.
     * Command words are bye, list, mark, unmark, todo, deadline, event, delete
     *
     * @param input string command given by user
     * @param taskList Tasklist object created with Sakura
     */
    public static void parseCommand(String input, TaskList taskList) {
        Sakura.ui.printDiv();
        if (input.equals("bye")) {
            Sakura.inProgress = false;
            exit();
        } else if (input.equals("list")) {
            Ui.showAllTask(taskList.tasks);
        } else if (input.toLowerCase().startsWith("mark")) {
            taskList.markTask(input);
        } else if (input.toLowerCase().startsWith("unmark")) {
            taskList.unmarkTask(input);
        } else if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline") || input.toLowerCase().startsWith("event")) {
            taskList.addTask(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            taskList.deleteTask(input);
        } else {
            SakuraException.genericTask();
        }
        Sakura.ui.printDiv();
    }

    /**
     * Saves user input data into storage database specified.
     * Display exit Ui interface.
     */
    private static void exit() {
        try {
            Sakura.storage.saveData(Sakura.taskList);
            Sakura.ui.showExit();
        } catch (IOException e) {
            SakuraException.saveError();
        }
    }
}
