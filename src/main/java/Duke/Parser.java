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
        int tasksSize = tasks.getSize();
        String[] strs = command.split(" ");
        if (command.equals("bye")) {
            ui.closeApplication();
        } else if (command.equals("list")) {
            mainWindow.printList(tasks);
        } else if (strs.length == 2 && (strs[0].equals("mark") || strs[0].equals("unmark"))) {
            assert java.util.regex.Pattern.matches("\\d+", strs[1]) : "The 2nd parameter should be an integer";
            int index = Integer.parseInt(strs[1]) - 1;
            assert index <= tasksSize : "the number to be deleted should be less than or equals to " + tasksSize;
            assert index > 0 : "the number to be deleted shouldn't be less than or equals to 0";
            if (strs[0].equals("mark")) {
                tasks.markTaskAsDone(index);
            } else if (strs[0].equals("unmark")) {
                tasks.unMarkTaskAsDone(index);
            }
        } else if (strs.length == 2 && (strs[0].equals("delete"))) {
            assert java.util.regex.Pattern.matches("\\d+", strs[1]) : "The 2nd parameter should be an integer";
            int index = Integer.parseInt(strs[1]) - 1;
            assert index <= tasksSize : "the number to be deleted should be less than or equals to " + tasksSize;
            assert index > 0 : "the number to be deleted shouldn't be less than or equals to 0";
            tasks.delete(index);
        } else if (strs.length == 2 && (strs[0].equals("find"))) {
            ArrayList<Task> tempTasks = new ArrayList<>();
            for (int i = 0; i < tasksSize; i++) {
                if (tasks.getTask(i).getTask().contains(strs[1])) {
                    tempTasks.add(tasks.getTask(i));
                }
            }
            mainWindow.printFindTasks(tempTasks);
        } else {
            try {
                String[] details;
                Task task;
                switch (strs[0]) {
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
                    mainWindow.printDontUnderstandMsg();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                mainWindow.printDescriptionCantBeEmptyMsg(strs[0]);
            }
        }
    }
}
