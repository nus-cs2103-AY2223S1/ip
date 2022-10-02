package duke;

import java.util.ArrayList;

/**
 * The class make sense of the user command
 *
 * @author LimWeiJun
 */
public class Parser {
    /**
     * Reads the given command and execute as follows
     *
     * @param ui of type UI
     * @param command of type String
     * @param tasks of type TaskList
     * @param mainWindow of type MainWindow
     */
    public static void readLine(Ui ui, String command, TaskList tasks, MainWindow mainWindow) {
        String[] strArr = command.split(" ");
        if (command.equals("bye")) {
            ui.closeApplication();
        } else if (command.equals("list")) {
            mainWindow.printList(tasks);
        } else if (strArr.length == 2 && (strArr[0].equals("mark") || strArr[0].equals("unmark"))) {
            markUnmarkTask(strArr, tasks);
        } else if (strArr.length == 2 && (strArr[0].equals("delete"))) {
            deleteTask(strArr, tasks);
        } else if (strArr.length == 2 && (strArr[0].equals("find"))) {
            findTask(strArr, tasks, mainWindow);
        } else if (strArr.length == 5 && (strArr[0].equals("update") && strArr[1].equals("datetime"))) {
            updateDateTime(strArr, tasks);
        } else {
            handleTodoDeadlineEvent(strArr, command, tasks, mainWindow);
        }
    }

    /**
     * Marks or unmarks a task
     *
     * @param strArr of type String[]
     * @param tasks of type TaskList
     */
    private static void markUnmarkTask(String[] strArr, TaskList tasks) {
        assert java.util.regex.Pattern.matches("\\d+", strArr[1]) : "The 2nd parameter should be a "
                                                                          + "positive integer";
        int index = Integer.parseInt(strArr[1]) - 1;
        assert index != -1 : "the 2nd parameter should start from index 1";
        assert index < tasks.getSize() : "the 2nd parameter to be mark or unmark should be less than or equals to "
                                         + tasks.getSize();
        if (strArr[0].equals("mark")) {
            tasks.markTaskAsDone(index);
        } else if (strArr[0].equals("unmark")) {
            tasks.unMarkTaskAsDone(index);
        }
    }

    /**
     * Deletes a task
     *
     * @param strArr of type String[]
     * @param tasks of type TaskList
     */
    private static void deleteTask(String[] strArr, TaskList tasks) {
        assert java.util.regex.Pattern.matches("\\d+", strArr[1]) : "The 2nd parameter should be a "
                                                                          + "positive integer";
        int index = Integer.parseInt(strArr[1]) - 1;
        assert index != -1 : "the 2nd parameter should start from index 1";
        assert index < tasks.getSize() : "the 2nd parameter should be less than or equals to " + tasks.getSize();
        tasks.deleteTask(index);
    }

    /**
     * Finds tasks that match with given keyword
     *
     * @param strArr of type String[]
     * @param tasks of type TaskList
     * @param mW of type MainWindow
     */
    private static void findTask(String[] strArr, TaskList tasks, MainWindow mW) {
        ArrayList<Task> tempTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getTask().contains(strArr[1])) {
                tempTasks.add(tasks.getTask(i));
            }
        }
        mW.printFindTasks(tempTasks);
    }

    /**
     * Updates a task with a new datetime
     *
     * @param strArr of type String[]
     * @param tasks of type TaskList
     */
    private static void updateDateTime(String[] strArr, TaskList tasks) {
        int index = Integer.parseInt(strArr[2]) - 1;
        String newDateStr = strArr[3] + " " + strArr[4];
        tasks.updateTask(index, newDateStr);
    }

    /**
     * Handles the rest of the commands
     *
     * @param strArr of type String[]
     * @param command of type String
     * @param tasks of type TaskList
     * @param mW of type MainWindow
     */
    private static void handleTodoDeadlineEvent(String[] strArr, String command, TaskList tasks, MainWindow mW) {
        try {
            String[] details;
            Task task;
            switch (strArr[0]) {
                case "deadline":
                    details = command.split(" ", 2)[1].split(" /by ");
                    task = new Deadline(details[0], false, details[1]);
                    tasks.addTask(task);
                    break;
                case "event":
                    details = command.split(" ", 2)[1].split(" /at ");
                    task = new Event(details[0], false, details[1]);
                    tasks.addTask(task);
                    break;
                case "todo":
                    String detail = command.split(" ", 2)[1];
                    task = new Todo(detail, false);
                    tasks.addTask(task);
                    break;
                default:
                    mW.printDontUnderstandMsg();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            mW.printDescriptionCantBeEmptyMsg(strArr[0]);
        }
    }
}
