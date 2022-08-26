package duke;

public class Ui {

    /**
     * Creates a new Ui object.
     */
    public Ui() {
    }

    /**
     * Greets the user via a print statement.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
    }

    /**
     * Displays the specified loading error message to the user.
     * @param e The error whose message will be shown to the user.
     */
    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Displays the specified error message to the user.
     * @param e The error whose message will be shown to the user.
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Displays a formatted version of the specified TaskList.
     * @param tasks The TaskList to be converted into a user-friendly display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Display for users to notify them that the specified task has been marked as completed.
     * @param t The Task that has been marked as completed.
     */
    public void showMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + t.toString());
    }

    /**
     * Display for users to notify them that the specified task has been marked as incomplete.
     * @param t The Task that has been marked as incomplete.
     */
    public void showUnmarked(Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n" + t.toString());
    }

    /**
     * Display for users to notify them that the specified task has been added to the TaskList.
     * @param tasks The TaskList where the specified task has been added to.
     */
    public void showAddedTask(TaskList tasks) {
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() -1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Display for users to notify them that the specified task has been removed from the TaskList.
     * @param t The TaskList from which the specified Task has been removed from.
     */
    public void showRemoved(Task t) {
        System.out.println("Noted. I've removed this task:\n" + t);
    }

    /**
     * Display for users to notify them that the system is saving their task list.
     */
    public void showBye() {
        System.out.println("Thank you for the great chat!\n\nSaving your current task list...");
    }

    /**
     * Display for users to notify them that the system has successfully saved their task list.
     */
    public void showSaved() {
        System.out.println("Your task list has been successfully saved.");
    }

    public void showFound(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showUnknown() {
        System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
    }
}
