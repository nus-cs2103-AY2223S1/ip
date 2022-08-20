import java.util.List;
import java.util.Scanner;
public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showList(TaskList tl) {
        List<Task> taskList = tl.getTasks();
        int index = 1;
        System.out.println("Here are the tasks in your list nya:");
        for (Task t : taskList) {
            System.out.println(index + "." + t);
            index++;
        }
    }

    public void showWelcomeMessage() {
        System.out.print("Hi I'm catBot!\nHow can I help you nya?\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showAddTask(Task task, int size) {
        System.out.println("Roger nya! Added this task:\n  " + task.toString());
        System.out.println("Now you have " + size + " task(s) in the list nya");
    }

    public void showMarkTask() {
        System.out.println("I've marked this task as done. Great job nya!");
    }

    public void showUnmarkTask() {
        System.out.println("Roger nya! I've marked this task as not done.");
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println("Roger nya! I've removed this task:\n  " + task.toString());
        System.out.println("Now you have " + size + " task(s) left in the list.");
    }
    public void showLine() {

    }

    public void showGoodbyeMessage() {
        System.out.println("Bye nya! Hope to see you again nya");
    }
}
