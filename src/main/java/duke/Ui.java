package duke;

public class Ui {
    public Ui() {
    }

    public void greet() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
    }

    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + t.toString());
    }
    public void showUnmarked(Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n" + t.toString());
    }

    public void showAddedTask(TaskList tasks) {
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() -1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showRemoved(Task t) {
        System.out.println("Noted. I've removed this task:\n" + t);
    }

    public void showSaving() {
        System.out.println("Saving your current task list...");
    }

    public void showSaved() {
        System.out.println("Your task list has been successfully saved.");
    }

    public void showFound(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}
