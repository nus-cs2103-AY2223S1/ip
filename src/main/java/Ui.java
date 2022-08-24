import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        String str = scanner.nextLine();
        return str;
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void bye() {
        System.out.println("Sayonara, Adios!");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public static void list(ArrayList<Task> db) {
        System.out.println("Here are your list of tasks!");
        db.forEach(task -> System.out.println((db.indexOf(task) + 1)
                + ". "  + task.toString()));
        System.out.println("You have " + db.size() + " tasks in the list.");
    }
}
