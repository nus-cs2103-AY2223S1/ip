import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> listOfThings = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Special Commands
        String BYE = "bye";
        String LIST = "list";
        String MARK = "mark";
        String UNMARK = "unmark";

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(true) {
            String userInput = sc.nextLine();
            if (userInput.equals(BYE)) {
                break;
            } else if (userInput.equals(LIST)) {
                ListOut();
            } else if (userInput.substring(0, 4).equals(MARK)) {
                int taskNum = Integer.valueOf(userInput.substring(5, 6)) - 1;
                MarkTask(taskNum);
            } else if (userInput.substring(0, 6).equals(UNMARK)) {
                int taskNum = Integer.valueOf(userInput.substring(7, 8)) - 1;
                UnmarkTask(taskNum);
            } else {
                AddToList(userInput);
            }
        }

        Bye();
    }

    public static void AddToList(String str) {
        Task tempTask  = new Task(str);
        listOfThings.add(tempTask);
        System.out.println("--------------------------------");
        System.out.println("added: " + str);
        System.out.println("--------------------------------");
    }

    public static void ListOut() {
        int size = listOfThings.size();
        System.out.println("--------------------------------");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". [" +  listOfThings.get(i).getStatusIcon() + "] " +
                    listOfThings.get(i).getDescription());
        }
        System.out.println("--------------------------------");
    }

    public static void MarkTask(int taskNum) {
        Task currentTask = listOfThings.get(taskNum);
        currentTask.markAsDone();
        System.out.println("--------------------------------");
        System.out.println("Nice! I've marked this task as done:\n " + " [" + currentTask.getStatusIcon() + "] "
                + currentTask.getDescription());
        System.out.println("--------------------------------");

    }

    public static void UnmarkTask(int taskNum) {
        Task currentTask = listOfThings.get(taskNum);
        currentTask.markAsNotDone();
        System.out.println("--------------------------------");
        System.out.println("Ok, I've marked this task as not done yet:\n " + " [" + currentTask.getStatusIcon() + "] "
                + currentTask.getDescription());
        System.out.println("--------------------------------");
    }

    public static void Bye() {
        System.out.println("--------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }
}
