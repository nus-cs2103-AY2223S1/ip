package duke;

public class UserInterface {

    private TaskList taskList;

    /**
     * Constructor for UserInterface.
     *
     * @param taskList TaskList that UserInterface interfaces with.
     */
    public UserInterface(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints all tasks currently in taskList.
     */
    public void printList() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toUser());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints message to confirm addition of new Task.
     *
     * @param task The new Task added to taskList.
     */
    public void addTaskMessage(Task task) {
        int taskCount = taskList.size();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it, i've added this task:");
        System.out.println(task.toUser());
        System.out.println("You now have " + taskCount + " tasks in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints message to confirm marking of a Task as done.
     *
     * @param taskIndex Index of the task that was marked as done.
     */
    public void markTaskMessage(int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("I've marked this task as done");
        System.out.println(taskList.get(taskIndex).toUser());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints message to confirm unmarking of a Task as undone.
     *
     * @param taskIndex Index of the task that was marked as undone.
     */
    public void unmarkTaskMessage(int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Ok, i've unmarked this task");
        System.out.println(taskList.get(taskIndex).toUser());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints message to confirm deletion of new Task.
     *
     * @param list      TaskList that the Task was deleted from.
     * @param taskIndex Index of Task that was deleted.
     */
    public static void taskDeletedMessage(TaskList list, int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it, i've deleted this task");
        System.out.println(list.get(taskIndex).toUser());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints goodbye message.
     */
    public void sayBye() {
        System.out.println("bai bai");
    }

    /**
     * Prints message to inform user of error when loading file.
     */
    public static void showLoadingError() {
        System.out.println("Error loading file from specified path, creating new list");
    }
}
