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
     * Returns goodbye message.
     */
    public String sayBye() {
        return "bai bai";
    }

    /**
     * Returns all tasks currently in taskList.
     *
     * @return All tasks currently in tasklist.
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
     * Returns message to confirm marking of a Task as done.
     *
     * @param taskIndex Index of the task that was marked as done.
     */
    public String markTaskMessage(int taskIndex) {
        String output = "I've marked this task as done\n";
        output = output + taskList.get(taskIndex).toUser();
        return output;
    }

    /**
     * Returns message to confirm unmarking of a Task as done.
     *
     * @param taskIndex Index of the task that was marked as undone.
     */
    public String unmarkTaskMessage(int taskIndex) {
        String output = "Ok, i've unmarked this task\n";
        output = output + taskList.get(taskIndex).toUser();
        return output;
    }

    /**
     * Returns message to confirm deletion of new Task.
     *
     * @param taskIndex Index of Task that was deleted.
     */
    public String taskDeletedMessage(int taskIndex) {
        String output = "Got it, I've deleted this task\n";
        output = output + taskList.get(taskIndex).toUser();
        return output;
    }

    /**
     * Returns message to confirm addition of new Task.
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
     * Given a TaskList of query matches, returns the results.
     *
     * @param matches TaskList of query matches.
     * @return The output of Duke.
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

    /**
     * Returns output when user asks for help.
     *
     * @return The predetermined help message.
     */
    public String printHelp() {
        String output = "It looks like you're having some trouble!\n";
        output = output + "Here are some example usages:\n" +
                "   todo Laundry\n" +
                "   deadline Math Assignment /by 20/20/2020 1200\n" +
                "   event Concert /at 19/20/2020 1200\n" +
                "   list\n" +
                "   mark 1\n" +
                "   unmark 1\n" +
                "   find Laundry\n";
        return output;
    }

    /**
     * Returns output when user says hi.
     *
     * @return Returns the Hi message.
     */
    public String welcomeMessage() {
        String output = "Please use one of the 3 commands to add a task:\n";
        output = output +
                "   todo [TASK]\n" +
                "   deadline [TASK] /by DD/MM/YYYY HHmm\n" +
                "   event [TASK] /at DD/MM/YYYY HHmm\n";
        return output;
    }
}
