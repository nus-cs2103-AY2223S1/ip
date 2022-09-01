package sakura;

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
    public static String parseCommand(String input, TaskList taskList) {
        System.out.println(Sakura.ui.printDiv());
        if (input.equals("bye")) {
            Sakura.inProgress = false;
            return exit();
        } else if (input.equals("list")) {
            return Ui.showAllTask(taskList.tasks);
        } else if (input.toLowerCase().startsWith("mark")) {
            return taskList.markTask(input);
        } else if (input.toLowerCase().startsWith("unmark")) {
            return taskList.unmarkTask(input);
        } else if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline") || input.toLowerCase().startsWith("event")) {
            return taskList.addTask(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            return taskList.deleteTask(input);
        } else if (input.toLowerCase().startsWith("find")) {
            return taskList.searchTask(input);
        } else {
            return SakuraException.genericTask();
        }
    }

    /**
     * Saves user input data into storage database specified.
     * Display exit Ui interface.
     */
    private static String exit() {
        try {
            Sakura.storage.saveData(Sakura.taskList);
            return Sakura.ui.showExit();
        } catch (IOException e) {
            return SakuraException.saveError();
        }
    }
}
