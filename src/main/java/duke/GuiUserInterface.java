package duke;

public class GuiUserInterface {
    private TaskList taskList;
    /**
     * Constructor for UserInterface.
     *
     * @param taskList TaskList that UserInterface interfaces with.
     */
    public GuiUserInterface(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints goodbye message.
     */
    public String sayBye() {
        return "bai bai";
    }

    /**
     * Prints all tasks currently in taskList.
     */
    public String printList() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toUser());
            output = output + (i + 1) + "." + taskList.get(i).toUser() + "\n";
        }
        return output;
    }

    /**
     * Prints message to confirm marking of a Task as done.
     *
     * @param taskIndex Index of the task that was marked as done.
     */
    public String markTaskMessage(int taskIndex) {
        String output = "I've marked this task as done\n";
        output = output + taskList.get(taskIndex).toUser();
        return output;
    }

    /**
     * Prints message to confirm unmarking of a Task as done.
     *
     * @param taskIndex Index of the task that was marked as undone.
     */
    public String unmarkTaskMessage(int taskIndex) {
        String output = "Ok, i've unmarked this task\n";
        output = output + taskList.get(taskIndex).toUser();
        return output;
    }

    /**
     * Prints message to confirm deletion of new Task.
     *
     * @param taskIndex Index of Task that was deleted.
     */
    public String taskDeletedMessage(int taskIndex) {
        String output = "Got it, I've deleted this task\n";
        output = output + taskList.get(taskIndex).toUser();
        return output;
    }

    /**
     * Prints message to confirm addition of new Task.
     *
     * @param task The new Task added to taskList.
     */
    public String addTaskMessage(Task task) {
        int taskCount = taskList.size();
        String output = "Got it, I've added this task:\n";
        output = output + task.toUser() + "\n";
        output = output + "You now have " + taskCount + " tasks in the list.";
        return output;
    }

    /**
     * Given a TaskList of query matches, prints out the results.
     *
     * @param matches TaskList of query matches.
     */
    public String printMatches(TaskList matches) {
        String output = "";
        if (matches.size() == 0) {
            output = "Sorry, i couldn't find any matches!";
            return output;
        } else {
            output = "Here are your results:\n";
            for (int i = 0; i < matches.size(); i++) {
                output = output + (i + 1) + "." + matches.get(i).toUser() + "\n";
            }
            return output;
        }
    }
}
