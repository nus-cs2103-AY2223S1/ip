import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        myPrinter(" Hello! I'm Duke\n What can I do for you?");
        ArrayList<Task> myList = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            int itemNumber = 0;
            switch(userInput.split(" ")[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list: ");
                    for(int i = 0; i<myList.size(); i++) {
                        System.out.println(myList.get(i));
                    }
                    break;
                case "mark":
                    itemNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    myList.get(itemNumber).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    printItem(itemNumber, myList);
                    break;
                case "unmark":
                    itemNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    myList.get(itemNumber).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    printItem(itemNumber, myList);
                    break;
                case "todo":
                    System.out.println("Got it. I've added this task:");
                    Todo myTodo = new Todo(userInput.substring(5));
                    myList.add(myTodo);
                    System.out.println(myTodo);
                    System.out.println("Now you have " + myList.size() + " in the list.");
                    break;
                case "deadline":
                    System.out.println("Got it. I've added this task:");
                    Deadline myDeadline = new Deadline(userInput.substring(9).split("/by")[0], userInput.split("/by")[1]);
                    myList.add(myDeadline);
                    System.out.println(myDeadline);
                    System.out.println("Now you have " + myList.size() + " in the list.");
                    break;
                case "event":
                    System.out.println("Got it. I've added this task:");
                    Event myEvent = new Event(userInput.substring(6).split("/at")[0], userInput.split("/at")[1]);
                    myList.add(myEvent);
                    System.out.println(myEvent);
                    System.out.println("Now you have " + myList.size() + " in the list.");
                    break;
                default:
                    myList.add(new Task(userInput));
                    System.out.println("added: " + userInput);
            }
        }
        scanner.close();
    }

    private static void myPrinter(String myString) {
        System.out.println("____________________________________________________________");
        System.out.println(myString);
        System.out.println("____________________________________________________________");
    }

    private static void printItem(int itemNumber, ArrayList<Task> myList) {
        System.out.println("____________________________________________________________");
        System.out.println("["
                + myList.get(itemNumber).getStatusIcon() + "] "
                + myList.get(itemNumber).getDescription());
        System.out.println("____________________________________________________________");
    }
}
