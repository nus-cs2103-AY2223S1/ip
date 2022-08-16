/**
 * Class to represent the user interfaces
 */
public class UI {
    private final static String underline = "____________________________________________________________";
    private final static String indentation = "  ";

    /**
     * Method to show Welcome message at the start.
     */
    public void printGreetings() {
        System.out.println(indentation + underline);
        System.out.println(indentation + "Hello! I'm Duke\n" +
                indentation + "What can I do for you?");
        System.out.println(indentation + underline);
    }

    /**
     * Method to show Exit message at finish.
     */
    public void exit() {
        System.out.println(indentation + underline);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(indentation + underline);
    }

    /**
     * The method to show added task.
     * @param task
     */
    public void showAddOnTask(String task) {
        System.out.println(indentation + underline);
        System.out.println(indentation + task);
        System.out.println(indentation + underline);
    }

    /**
     * The method to show list of added tasks.
     * @param task
     */
    public void showListDetails(TaskList task) {
        System.out.println(indentation + underline);
        for (int i = 0; i < task.size(); i ++) {
            System.out.println(indentation + (i + 1) + "." + indentation + task.get(i));
        }
        System.out.println(indentation + underline);
    }
}
