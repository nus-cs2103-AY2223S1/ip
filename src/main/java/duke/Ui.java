package duke;

public class Ui {
    protected String indent;
    protected String divider;
    protected String logo;
    protected String openingStatement;
    protected String exitStatement;

    public Ui() {
        this.indent = "     ";
        this.divider = "  ____________________________________________________________\n";
        this.logo = this.indent + "____        _        \n"
                + this.indent + "|  _ \\ _   _| | _____ \n"
                + this.indent + "| | | | | | | |/ / _ \\\n"
                + this.indent + "| |_| | |_| |   <  __/\n"
                + this.indent + "|____/ \\__,_|_|\\_\\___|\n\n";
        this.openingStatement = this.indent + "Hello! I'm Duke.\n"
                + this.indent + "What can I do for you?\n";
        this.exitStatement = this.indent + "Bye. Hope to see you again soon! :)\n";
    }

    public void showOpeningStatement() {
        System.out.println(this.divider + this.logo + this.openingStatement + this.divider);
    }

    public void showExitStatement() {
        System.out.println(this.divider + this.exitStatement + this.divider);
    }

    public void showLoadingError() {
        System.out.println(this.divider + this.indent + "Oops, there is a loading error!\n" + this.divider);
    }

    public void showTaskList(TaskList tasks) {
        String command = "Here are the tasks in your list:\n";
        System.out.println(this.divider + this.indent + command);
        System.out.println(tasks);
        System.out.println(this.divider);
    }

    public void showMarkDone(Task task) {
        String command = "Nice! I've marked this task as done:";
        System.out.println(this.divider + this.indent + command);
        System.out.println(this.indent + this.indent + task + "\n" + this.divider);
    }

    public void showUnmarkDone(Task task) {
        System.out.println(this.divider + this.indent + "Okay, I've marked this task as not done yet:\n");
        System.out.println(this.indent + this.indent + task + "\n" + this.divider);
    }

    public void showAddTaskDone(Task task, int numberOfTasks) {
        String command = "Got it. I've added this task:\n";
        System.out.println(this.divider + this.indent + command);
        System.out.println(this.indent + this.indent + task);
        System.out.println(this.indent + "Now you have " + numberOfTasks + " tasks in your list.\n" + this.divider);
    }

    public void showDeleteTaskDone(Task removedTask, int numberOfTasks) {
        System.out.println(this.divider + this.indent + "Noted, I've removed this task:");
        System.out.println(this.indent + this.indent + removedTask);
        System.out.println(this.indent + "Now you have " + numberOfTasks + " tasks in your list.\n" + this.divider);
    }

    public void askWhichTask() {
        System.out.println(this.divider + this.indent + "Wait, which task are you referring to?\n" + this.divider);
    }

    public void ShowFoundTasks(TaskList tasks) {
        String command = "Here are the matching tasks in your list:\n";
        System.out.println(this.divider + this.indent + command);
        System.out.println(tasks);
        System.out.println(this.divider);
    }

    public void showUnknownCommand() {
        System.out.println(this.divider + this.indent + "Oops, sorry! I don't know what that means :(\n" + this.divider);
    }

    public void printException(Exception e) {
        System.out.println(this.divider + this.indent + "Error: " + e + "\n" + this.divider);
    }
}