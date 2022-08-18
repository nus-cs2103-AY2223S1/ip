import java.security.spec.ECField;
import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];


    private boolean inputChecker(String[] arr) {
        if (arr.length  < 2) {
            return false;
        }
        return true;
    }

    private static void mark(String[] arr) {
        int i = Integer.parseInt(arr[1]);
        if (i  <= Task.getCount()) {
            tasks[i - 1].complete();
            System.out.println("Nice! I have marked this task as done: ");
            System.out.println(tasks[i - 1]);
        } else {
            System.out.println("Index does not exist");
        }

    }

    private static void unmark(String[] arr) {
        int i = Integer.parseInt(arr[1]);
        if (i <= Task.getCount()) {
            tasks[i - 1].incomplete();
            System.out.println("OK, I have marked this task as not done yet: ");
            System.out.println(tasks[i - 1]);
        } else {
            System.out.println("Index does not exist");
        }
    }

    private static void list() {
        for (int i = 0; i < Task.getCount(); i++) {
            if (tasks[i] == null) {
                break;
            }
            else {
                System.out.println((i+1) + ". " + tasks[i].toString());
            }
        }
    }

    private static void todo(String input) {
        Todo curr = new Todo(input);
        tasks[Task.getCount() - 1] = curr;
        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    private static void deadline (String input) {
        String arr[] = input.split("/by", 2);
        Deadline curr = new Deadline(arr[0], arr[1]);
        tasks[Task.getCount() - 1] = curr;
        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    private static void event(String input) {
        String arr[] = input.split("/at", 2);
        Event curr = new Event(arr[0], arr[1]);
        tasks[Task.getCount() - 1] = curr;
        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            String arr[] = input.split(" ", 2);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                list();
            }

            else if (arr[0].equals("mark")){
                mark(arr);
            }
            else if (arr[0].equals("unmark")) {
                unmark(arr);
            }

            else if (arr[0].equals("todo")) {
                todo(arr[1]);
            }

            else if (arr[0].equals("deadline")) {
                deadline(arr[1]);
            }
            else if (arr[0].equals("event")) {
                event(arr[1]);
            }

            else {
                System.out.println("Input not recognised! Please try again! ");
            }
        }

    }
}
