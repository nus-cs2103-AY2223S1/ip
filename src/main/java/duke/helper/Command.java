package duke.helper;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidTimeException;
import duke.exception.NoDescriptionException;
import duke.task.ErrorTask;
import duke.task.Task;
import duke.task.TaskCreator;
import duke.task.TaskList;

/**
 * Class to contain all the commands for parse
 */
public class Command {

    /**
     * Method to find task given a String and TaskList
     *
     * @param in the input command given
     * @param list the list to find the task from
     * @return the string representing the task found
     */
    public static String find(String in, TaskList list) {
        String keywords = in.split(" ", 2)[1];
        return list.find(keywords);
    }

    /**
     * Method to clear the list given
     *
     * @param list the list to be cleared
     * @return the string message of clear
     */
    public static String clear(TaskList list) {
        return list.clear();
    }

    /**
     * Method to list out the tasks in the list given
     *
     * @param list the list to be listed out
     * @return the string of the list
     */
    public static String list(TaskList list) {
        String message = list.printTasks();
        if (message == "") {
            return "Woohoo! You are all out of tasks!";
        }
        return message;
    }

    /**
     * Method to mark a task given a String and TaskList
     *
     * @param in the input command given
     * @param list the list to mark the task from
     * @return the string message of mark
     */
    public static String mark(String in, TaskList list) {
        int index = Integer.valueOf(in.split(" ")[1]) - 1;
        return list.mark(index);
    }

    /**
     * Method to unmark a task given a String and TaskList
     *
     * @param in the input command given
     * @param list the list to unmark the task from
     * @return the string message of unmark
     */
    public static String unmark(String in, TaskList list) {
        int index = Integer.valueOf(in.split(" ")[1]) - 1;
        return list.unmark(index);
    }

    /**
     * Method to delete a task given a String and TaskList
     *
     * @param in the input command given
     * @param list the list to delete the task from
     * @return the string message of delete with the task deleted
     */
    public static String delete(String in, TaskList list) {
        int index = Integer.valueOf(in.split(" ")[1]) - 1;
        String message;

        try {
            if (index >= list.getSize()) {
                throw new InvalidCommandException();
            }
            Task task = list.getTask(index);
            message = Ui.delete(task) + "\n";
            list.delete(index);
            message += Ui.countTasks(list);
            return message;
        } catch (InvalidCommandException e) {
            return e.toString();
        }
    }

    /**
     * Method to create Task
     *
     * @param in the input string given
     * @param list the tasklist to add the task to
     * @return a string describing if the task creation was successful
     */
    public static String createTask(String in, TaskList list) {
        String message;
        try {
            Task task = TaskCreator.createTask(in);
            if (task == null) {
                throw new InvalidCommandException();
            } else if (task.getClass() == ErrorTask.class && task.getDescription() == "invalidTime") {
                throw new InvalidTimeException();
            } else if (task.getClass() == ErrorTask.class && task.getDescription() == "invalidDate") {
                throw new InvalidDateException();
            } else if (task.getDescription().length() < 1) {
                throw new NoDescriptionException();
            } else {
                list.add(task);
                message = Ui.add(task);
                return message;
            }
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String help() {
        return HelpList.help();
    }
}
