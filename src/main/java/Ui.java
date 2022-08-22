import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String SPACING = "--------------------------------------";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(Ui.SPACING + "\n" +
                "Hello! Blink here\n" +
                "What can I do for you today?\n" +
                Ui.SPACING);
    }

    public void showBye() {
        System.out.println("Bye bye~ Glad to be of service :D");
    }


    public void showLine() {
        System.out.println(Ui.SPACING);
    }

    public void showError(String errMessage) {
        System.out.println("Error found: " + errMessage);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showList(TaskList tasks) {
        System.out.println(tasks.listTask());
    }

    public void mark(TaskList tasks, int num) {
        Task task = tasks.get(num - 1);
        System.out.println("This task has been marked as done\n" + task);
    }

    public void unMark(TaskList tasks, int num) {
        Task task = tasks.get(num - 1);
        System.out.println("This task has been marked as done\n" + task);
    }

    public void deleteTask(TaskList tasks, Task task) {
        System.out.println("Task has been deleted successfully.\n" + task +
               "\n" + tasks.deleted());
    }

    public void showFilter(ArrayList<Task> tasks, LocalDate date) {
        if (tasks.size() == 0) {
            System.out.println("No task found on " + date.toString());
        } else {
            System.out.println(tasks.size() + ((tasks.size() == 1)? " task": " tasks") +
                    " found");
            for (int y = 0; y < tasks.size(); y++) {
                System.out.println(tasks.get(y));
            }
        }
    }

    public void addTask(TaskList tasks, Task task) {
        System.out.println("Alright, this task has been successfully added!\n" +
                task + "\nTotal of " + tasks.length() + " tasks now");
    }

}
