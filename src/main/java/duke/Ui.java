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
    public String greet() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        return "Welcome to DukePro. An improved version of the Duke tasklist "
                + "chat bot, created by JavaFX. How may I help you today?";
    }

    /**
     * Displays the specified loading error message to the user.
     * @param e The error whose message will be shown to the user.
     */
    public String showLoadingError(Exception e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    /**
     * Displays the specified error message to the user.
     * @param e The error whose message will be shown to the user.
     */
    public String showError(Exception e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    /**
     * Displays a formatted version of the specified TaskList.
     * @param tasks The TaskList to be converted into a user-friendly display.
     */
    public String showTaskList(TaskList tasks) {
        String toReturn = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            toReturn = toReturn + (i + 1) + "." + tasks.get(i) + "\n";
        }
        System.out.println(toReturn);
        return toReturn;
    }

    /**
     * Display for users to notify them that the specified task has been marked as completed.
     * @param t The Task that has been marked as completed.
     */
    public String showMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + t.toString());
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    /**
     * Display for users to notify them that the specified task has been marked as incomplete.
     * @param t The Task that has been marked as incomplete.
     */
    public String showUnmarked(Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n" + t.toString());
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }

    /**
     * Display for users to notify them that the specified task has been added to the TaskList.
     * @param tasks The TaskList where the specified task has been added to.
     */
    public String showAddedTask(TaskList tasks) {
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        String toReturn = "Got it. I've added this task:\n" + tasks.get(tasks.size() - 1);
        toReturn += "\nNow you have " + tasks.size() + " tasks in the list.";
        return toReturn;
    }

    /**
     * Display for users to notify them that the specified task has been removed from the TaskList.
     * @param t The TaskList from which the specified Task has been removed from.
     */
    public String showRemoved(Task t) {
        System.out.println("Noted. I've removed this task:\n" + t);
        return "Noted. I've removed this task:\n" + t.toString();
    }

    /**
     * Display for users to notify them that the system is saving their task list.
     */
    public String showBye() {
        System.out.println("Thank you for the great chat!\n\nYour task list has been successfully saved.");
        return "Thank you for the great chat!\n\nYour task list has been successfully saved.";
    }

    /**
     * Display for users to notify them that the system has successfully saved their task list.
     */
    public String showSaved() {
        System.out.println("Your task list has been successfully saved.");
        return "Your task list has been successfully saved.";
    }

    public String showFound(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        String toReturn = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            toReturn = toReturn + (i + 1) + "." + tasks.get(i) + "\n";
        }
        return toReturn;
    }

    public String showUnknown() {
        System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
        return "OOPS!! I'm sorry, but I don't know what that means :-(";
    }
}
