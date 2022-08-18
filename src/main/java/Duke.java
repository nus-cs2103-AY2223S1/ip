import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> tasks = new ArrayList<>();

    public static void printLine() {
        System.out.println("-".repeat(100));
    }

    public static void greetings() {
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void listAllItems() {
        printLine();
        for(int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            System.out.println(number + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    public static void addAnItem(String item) {
        Task newTask = new Task(item);
        tasks.add(newTask);
        printLine();
        System.out.println("added: " + item);
        printLine();
    }

    public static void mark(int num) {
        printLine();
        tasks.get(num - 1).mark();
        System.out.println("OK, I've marked this task as done:");
        System.out.println(tasks.get(num - 1).toString());
        printLine();
    }

    public static void unmark(int num) {
        printLine();
        tasks.get(num - 1).unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(num - 1).toString());
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

       greetings();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            switch(command) {
                case "bye":
                    printBye();
                    return;
                case "list":
                    listAllItems();
                    break;
                case "mark":
                    int num1 = Integer.parseInt(input.split(" ")[1]);
                    mark(num1);
                    break;
                case "unmark":
                    int num2 = Integer.parseInt(input.split(" ")[1]);
                    ummark(num2);
                    break;
                default:
                    addAnItem(input);
                    break;
            }
        }
    }


}
