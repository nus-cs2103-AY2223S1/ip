package Duke;

/**
 * This is the class that handles
 * all the user interfaces of the program
 */
public class Graphics {

    private TaskList taskList;

    /**
     * Constructor for graphics
     * @param taskList Wraps a TaskList object to add
     *                 graphics to that task
     */
    public Graphics(TaskList taskList) {

        this.taskList = taskList;
    }

    /**
     * Method to show the user their list of tasks
     */
    void printList() {
        System.out.println("____________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
        System.out.println("____________________________________");
    }

    /**
     * Method to show the message when
     * user add a task to the list
     * @param task The type of task that is chosen
     *             by the user
     */
    void addMessage(Task task) {
        int taskCount = taskList.size();
        System.out.println("____________________________________");
        System.out.println("Got it, i've added this task:");
        System.out.println(task.toString());
        System.out.println("You now have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________");
    }

    /**
     * Method to show the message when the user mark
     * a task in their task list as done
     * @param taskIndex The position of the task in the list
     */
    void markMessage(int taskIndex) {
        System.out.println("____________________________________");
        System.out.println("I've marked this task as done");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println("____________________________________");
    }

    /**
     * Method to show the message when the user unmark a
     * task in their task list
     * @param taskIndex The position of the task in the list
     */
    public  void unmarkMessage(int taskIndex) {
        System.out.println("____________________________________");
        System.out.println("Ok, i've unmarked this task");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println("____________________________________");
    }

    /**
     * Method to show the message when the user delete a task from
     * their task list
     * @param list      The list of tasks
     * @param taskIndex The position of the task in the list
     */
    public static void deleteMessage(TaskList list, int taskIndex) {
        System.out.println("____________________________________");
        System.out.println("Got it, i've deleted this task");
        System.out.println(list.get(taskIndex).toString());
        System.out.println("____________________________________");
    }

    /**
     * Method to show the exit message when
     * user exit the program
     */
    void sayGoodbye() {
        System.out.println("Why you dowan me :(");
    }

    public static void loadingError() {
        System.out.println("Error loading file from specified path, creating new list");
    }
}