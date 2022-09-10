package Duke;

import java.util.ArrayList;

/**
 * The class make sense of the user command
 *
 * @author LimWeiJun
 */
public class Parser {
    /**
     * The method takes in a parameter of type TaskList
     *
     * @param tasks of type TaskList
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
        } else {
            handleTodoDeadlineEvent(strArr, command, tasks, mainWindow);
        }
    }

    private static void markUnmarkTask(String[] strArr, TaskList tasks) {
        assert java.util.regex.Pattern.matches("\\d+", strArr[1]) : "The 2nd parameter should be a " +
                                                                    "positive integer";
        int index = Integer.parseInt(strArr[1]) - 1;
        assert index < tasks.getSize() : "the index to be deleted should be less than " + tasksSize;
        if (strArr[0].equals("mark")) {
            tasks.markTaskAsDone(index);
        } else if (strArr[0].equals("unmark")) {
            tasks.unMarkTaskAsDone(index);
        }
    }

    private static void deleteTask(String[] strArr, TaskList tasks) {
        assert java.util.regex.Pattern.matches("\\d+", strArr[1]) : "The 2nd parameter should be a " +
                                                                     "positive integer";
        int index = Integer.parseInt(strArr[1]) - 1;
        assert index < tasks.getSize() : "the index to be deleted should be less than " + tasksSize;
        tasks.delete(index);
    }

    private static void findTask(String[] strArr, TaskList tasks, MainWindow mW) {
        ArrayList<Task> tempTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getTask().contains(strArr[1])) {
                tempTasks.add(tasks.getTask(i));
            }
        }
        mW.printFindTasks(tempTasks);
    }

    private static void handleTodoDeadlineEvent(String[] strArr, String command, TaskList tasks, MainWindow mW) {
        try {
            String[] details;
            Task task;
            switch (strArr[0]) {
                case "deadline":
                    details = command.split(" ", 2)[1].split(" /by ");
                    task = new Deadline(details[0], false, details[1]);
                    tasks.add(task);
                    break;
                case "event":
                    details = command.split(" ", 2)[1].split(" /at ");
                    task = new Event(details[0], false, details[1]);
                    tasks.add(task);
                    break;
                case "todo":
                    String detail = command.split(" ", 2)[1];
                    task = new Todo(detail, false);
                    tasks.add(task);
                    break;
                default:
                    mW.printDontUnderstandMsg();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            mW.printDescriptionCantBeEmptyMsg(strArr[0]);
        }
    }
}
