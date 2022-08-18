import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        askUser();
        sayBye();
    }

    public static void greetUser() {
        System.out.println("\t______________________________________________________");
        System.out.println("\tHey there! I'm Arias!");
        System.out.println("\tHow may I help you? :)");
        System.out.println("\t______________________________________________________\n");
    }

    public static void sayBye() {
        System.out.println("\t______________________________________________________");
        System.out.println("\tNice seeing you! Bye!");
        System.out.println("\t______________________________________________________\n");
    }

    public static void askUser() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        while (!userInput.equals("bye") && !userInput.equals("Bye")) {
            if (userInput.equals("list") || userInput.equals("List")) {
                System.out.println("\t______________________________________________________");
                listUserTasks();
                System.out.println("\t______________________________________________________\n");
            } else if (userInput.equals("mark") || userInput.equals("Mark")) {
                int taskNumber = Integer.parseInt(sc.next());
                mark(taskNumber);
            } else if (userInput.equals("unmark") || userInput.equals("Unmark")) {
                int taskNumber = Integer.parseInt(sc.next());
                unmark(taskNumber);
            } else {
                addUserTasks(userInput);
                System.out.println("\t______________________________________________________");
                System.out.println("\t" + userInput);
                System.out.println("\t______________________________________________________\n");
            }
            userInput = sc.next();
        }
    }

    public static void addUserTasks(String userInput) {
        Task t = new Task(userInput);
        tasks.add(t);
    }

    public static void listUserTasks() {
        if (tasks.size() == 0) {
            System.out.println("\tYou do not have any tasks!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + ". [" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
            }
        }
    }

    public static void mark(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
        System.out.println("\t______________________________________________________");
        System.out.println("\tAlright! Marked this task as done!");
        System.out.println("\t\t[X] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println("\t______________________________________________________\n");
    }

    public static void unmark(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
        System.out.println("\t______________________________________________________");
        System.out.println("\tOkay! Unmarked this task!");
        System.out.println("\t\t[ ] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println("\t______________________________________________________\n");
    }
}
