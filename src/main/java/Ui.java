import java.util.Scanner;
public class Ui {
    private static final String GREETINGS = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LINE = "______________________________________________________________________________";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String ADD_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    Scanner sc = new Scanner(System.in);
    protected void greetings() {
        System.out.println(LINE);
        System.out.println(GREETINGS);
        System.out.println(LINE);
    }
    protected void exit() {
        System.out.println(EXIT_MESSAGE);
    }
    protected void lineBreak() {
        System.out.println(LINE);
    }
    protected void showError(String message) {
        System.err.println(message);
    }
    protected void addTaskMessage(Task task, TaskList tasks) {
        System.out.println(ADD_MESSAGE);
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    protected void displayTasks(TaskList tasks) throws DukeException {
        System.out.println(LIST_MESSAGE);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }
    }
    protected void delTaskMessage(Task task, TaskList tasks) {
        System.out.println(DELETE_MESSAGE);
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    protected void markDone(Task task) {
        System.out.println(DONE_MESSAGE);
        System.out.println(task.toString());
    }
    protected void markUndone(Task task) {
        System.out.println(UNDONE_MESSAGE);
        System.out.println(task.toString());
    }
    protected String readCommand() {
        return sc.nextLine();
    }
}
