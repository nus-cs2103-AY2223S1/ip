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
    public String printList() {
        String response = "";
        System.out.println("____________________________________");
        response += "____________________________________\n";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
            response += i + 1 + "." + taskList.get(i).toString() + "\n";
        }
        System.out.println("____________________________________");
        response += "____________________________________";
        return response;
    }

    /**
     * Method to show the message when
     * user add a task to the list
     * @param task The type of task that is chosen
     *             by the user
     */
    public String addMessage(Task task) {
        String response = "";
        int taskCount = taskList.size();
        System.out.println("____________________________________");
        System.out.println("Got it, i've added this task:");
        System.out.println(task.toString());
        System.out.println("You now have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________");
        response += "____________________________________\n";
        response += "Got it, i've added this task:\n";
        response += task.toString() + "\n";
        response += "You now have " + taskCount + " tasks in the list.\n";
        response += "____________________________________";
        return response;
    }

    /**
     * Method to show the message when the user mark
     * a task in their task list as done
     * @param taskIndex The position of the task in the list
     */
    public String markMessage(int taskIndex) {
        String response = "";
        System.out.println("____________________________________");
        System.out.println("I've marked this task as done");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println("____________________________________");
        response += "____________________________________\n";
        response += "I've marked this task as done\n";
        response += taskList.get(taskIndex).toString() + "\n";
        response += "____________________________________";
        return response;
    }

    /**
     * Method to show the message when the user unmark a
     * task in their task list
     * @param taskIndex The position of the task in the list
     */
    public String unmarkMessage(int taskIndex) {
        String response = "";
        System.out.println("____________________________________");
        System.out.println("Ok, i've unmarked this task");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println("____________________________________");
        response += "____________________________________\n";
        response += "Ok, i've unmarked this task\n";
        response += taskList.get(taskIndex).toString() + "\n";
        response += "____________________________________";
        return response;
    }

    /**
     * Method to show the message when the user delete a task from
     * their task list
     * @param list      The list of tasks
     * @param taskIndex The position of the task in the list
     */
    public static String deleteMessage(TaskList list, int taskIndex) {
        String response = "";
        System.out.println("____________________________________");
        System.out.println("Got it, i've deleted this task");
        System.out.println(list.get(taskIndex).toString());
        System.out.println("____________________________________");
        response += "____________________________________\n" ;
        response += "Got it, i've deleted this task\n";
        response += list.get(taskIndex).toString() + "\n";
        response += "____________________________________";
        return response;
    }

    /**
     * Method to show the exit message when
     * user exit the program
     */
    public String sayGoodbye() {

        System.out.println("Why you dowan me :(");
        return "Why you dowan me :(";
    }

    public String printSearches(TaskList tasks) {
        String response = "";
        System.out.println("____________________________________");
        if (tasks.size() == 0) {
            System.out.println("Sorry there are no tasks matching your keyword");
            return "Sorry there are no tasks matching your keyword";
        } else {
            System.out.println("Are these what you are looking for:");
            response = response + "Are these what you are looking for:\n";
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(1 + i + "." + tasks.get(i).toString());
                response += 1 + i + "." + tasks.get(i).toString() + "\n";

            }
        }
        System.out.println("____________________________________");
        response = response + "\n" + "____________________________________";
        return response;
    }

    public static String loadingError() {
        System.out.println("Error loading file from specified path, creating new list");
        return "Error loading file from specified path, creating new list";
    }
}