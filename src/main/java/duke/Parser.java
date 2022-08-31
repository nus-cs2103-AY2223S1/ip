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
    public static void parse(String item, TaskList taskList) {
        if (item.equals("list")) {
            taskList.printTaskList();
        } else if (item.length() == 6 && item.substring(0, 4).equals("mark")) {
            int index = Integer.parseInt(item.substring(5)) - 1;
            taskList.setDone(index);
        } else if (item.length() == 8 && item.substring(0, 6).equals("unmark")) {
            int index = Integer.parseInt(item.substring(7)) - 1;
            taskList.setUndone(index);
        } else if (item.length() >= 8 && item.substring(0, 8).equals("deadline")) {
            taskList.createDeadline(item);
        } else if (item.length() >= 5 && item.substring(0, 5).equals("event")) {
            taskList.createEvent(item);
        } else if (item.length() >= 4 && item.substring(0, 4).equals("todo")) {
            taskList.createTask(item);
        } else if (item.length() >= 6 && item.substring(0, 6).equals("delete")) {
            int index = Integer.parseInt(item.substring(7)) - 1;
            taskList.deleteTask(index);
        } else if (item.length() >= 4 && item.substring(0, 4).equals("find")) {
            taskList.printFilteredTaskList(item.substring(5));
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
