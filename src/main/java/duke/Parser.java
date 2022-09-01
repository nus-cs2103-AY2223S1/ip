package duke;

/**
 * Represents the parsing aspects of the program
 */
public class Parser {

    /**
     * Provides approproite responses to input given by user
     * @param item the description of the task
     * @param taskList the object used to store the tasks
     */
    public static String parse(String item, TaskList taskList) {
        if (item.equals("list")) {
            return taskList.printTaskList();
        } else if (item.length() == 6 && item.substring(0, 4).equals("mark")) {
            int index = Integer.parseInt(item.substring(5)) - 1;
            return taskList.setDone(index);
        } else if (item.length() == 8 && item.substring(0, 6).equals("unmark")) {
            int index = Integer.parseInt(item.substring(7)) - 1;
            return taskList.setUndone(index);
        } else if (item.length() >= 8 && item.substring(0, 8).equals("deadline")) {
            return taskList.createDeadline(item);
        } else if (item.length() >= 5 && item.substring(0, 5).equals("event")) {
            return taskList.createEvent(item);
        } else if (item.length() >= 4 && item.substring(0, 4).equals("todo")) {
            return taskList.createTask(item);
        } else if (item.length() >= 6 && item.substring(0, 6).equals("delete")) {
            int index = Integer.parseInt(item.substring(7)) - 1;
            return taskList.deleteTask(index);
        } else if (item.length() >= 4 && item.substring(0, 4).equals("find")) {
            return taskList.printFilteredTaskList(item.substring(5));
        } else {
            return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
