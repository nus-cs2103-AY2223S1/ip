import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> storeList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();

        Scanner scanner = new Scanner(System.in);
        String userInput = "";



        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if(userInput.equals("list")) {
                list();
                continue;
            }
            if(userInput.equals("bye")) {
                break;
            }
            if(userInput.split(" ", 2)[0].equals("mark")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                mark(inputTaskIndex);
                continue;
            }
            if(userInput.split(" ", 2)[0].equals("unmark")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                unmark(inputTaskIndex);
                continue;
            }
            addTask(userInput);
        }

        farewell();

    }

    public static void greeting() {
        String greet = "Hello! I'm Duke \n"
                + "What can I do for you? \n";
        System.out.println(greet);
    }

    public static void echo(String userInput) {
        System.out.println(userInput);
    }

    public static void addTask(String userInput) {
        Task t = new Task(userInput);
        storeList.add(t);
        System.out.println("added: " + t.description);
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i < storeList.size(); i++){
//            System.out.printf("%i. [%c] %s", i + 1, storeList.get(i).getStatusIcon(), storeList.get(i).description);
            System.out.println(i + 1 + ". " + "[" + storeList.get(i).getStatusIcon() + "] " + storeList.get(i).description);
        }
    }

    public static void mark(int taskIndex) {
        storeList.get(taskIndex).markAsDone();
    }

    public static void unmark(int taskIndex) {
        storeList.get(taskIndex).markAsNotDone();
    }

    public static void farewell() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
