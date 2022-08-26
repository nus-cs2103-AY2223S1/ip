package Duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello!, I'm Yiye.\nWhat can I do for you? ◠‿◠");
    }

    public void bye() {
        System.out.println("Bye! Hope to see you again soon!");
    }


    public String scan() {
        return scanner.nextLine();
    }

    public void listTask(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, list.get(i).toString());
        }
    }

    public void printTodo(Task todo) {
        System.out.println(todo.toString());
    }
    public void printEvent(Task event) {
        System.out.println(event.toString());
    }
    public void printDeadline(Task dl) {
        System.out.println(dl.toString());
    }

    public void printSummary(ArrayList<Task> list) {
        if (list.size()>1) {
            System.out.printf("Now you have %d tasks in your list.\n", list.size());
        } else {
            System.out.printf("Now you have %d task in your list.\n", list.size());
        }
    }

    public void notFound() {
        System.out.println("No tasks on this date, check you format! --> MMM(eg. Apr) dd yyy");
    }

    public void printMatchedTasks(ArrayList<Task> list) {
        System.out.println("Here are the matching tasks:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, list.get(i).toString());
        }
    }
    public void stop() {
        scanner.close();
    }

}